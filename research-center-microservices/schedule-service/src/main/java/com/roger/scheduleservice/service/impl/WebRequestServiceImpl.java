package com.roger.scheduleservice.service.impl;

import com.roger.microservices.exception.CustomNotFoundException;
import com.roger.microservices.exception.CustomWebServiceException;
import com.roger.microservices.exception.ServiceLayerExceptionCodes;
import com.roger.scheduleservice.dto.EquipmentDto;
import com.roger.scheduleservice.model.Order;
import com.roger.scheduleservice.service.WebRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
@AllArgsConstructor
public class WebRequestServiceImpl implements WebRequestService {
    private WebClient webClient;

    @Override
    public EquipmentDto getEquipmentByIdFromResearchCenterService(Long equipmentId) {
        String uri = "http://localhost:8080/api/v1/equipment/" + equipmentId.toString()
                + "/info";
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> {
                            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_EQUIPMENT_ID, equipmentId);
                        })
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> {
                            throw new CustomWebServiceException(ServiceLayerExceptionCodes.INTERNAL_SERVICE_ERROR + " " + clientResponse.statusCode());
                        })
                .bodyToMono(EquipmentDto.class).block();
    }
    @Override
    public List<Order> getOrderListByEquipmentIdInPeriod(Long equipmentId, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startDateTime = startPeriod.format(formatter);
        String endDateTime = endPeriod.format(formatter);

        String uri = "http://localhost:8081/api/v1/equipment/" + equipmentId.toString() +
                "/orders?startTime=" + startDateTime +
                "&endTime=" + endDateTime;
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> {
                            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_ORDERS, equipmentId);
                        })
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> {
                            throw new CustomWebServiceException(ServiceLayerExceptionCodes.INTERNAL_SERVICE_ERROR);
                        })
                .bodyToFlux(Order.class)
                .collectList()
                .block();
    }

}
