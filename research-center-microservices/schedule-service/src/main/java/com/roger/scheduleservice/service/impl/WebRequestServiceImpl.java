package com.roger.scheduleservice.service.impl;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenter.dto.OrderGetDto;
import com.roger.researchcenter.exception.CustomNotFoundException;
import com.roger.researchcenter.exception.CustomWebServiceException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
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
    private WebClient.Builder webClient;

    @Override
    public EquipmentDto getEquipmentByIdFromResearchCenterService(Long equipmentId) {
        String uri = "http://research-center-service/api/v1/equipment/" + equipmentId.toString()
                + "/info";
        return webClient.build().get()
                // todo inter-service communication .header(HttpHeaders.AUTHORIZATION, ???)

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
    public List<OrderGetDto> getOrderListByEquipmentIdInPeriod(Long equipmentId, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startDateTime = startPeriod.format(formatter);
        String endDateTime = endPeriod.format(formatter);

        String uri = "http://order-service/api/v1/orders/equipment/" + equipmentId.toString() +
                "?startTime=" + startDateTime +
                "&endTime=" + endDateTime;
        return webClient.build().get()
                // todo inter-service communication .header(HttpHeaders.AUTHORIZATION, ???)
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
                .bodyToFlux(OrderGetDto.class)
                .collectList()
                .block();
    }

}
