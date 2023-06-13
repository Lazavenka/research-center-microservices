package com.roger.orderservice.service.impl;

import com.roger.orderservice.dto.EquipmentDto;
import com.roger.orderservice.exception.CustomNotFoundException;
import com.roger.orderservice.exception.CustomWebServiceException;
import com.roger.orderservice.model.Order;
import com.roger.orderservice.service.ServiceLayerExceptionCodes;
import com.roger.orderservice.service.WebRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class WebRequestServiceImpl implements WebRequestService {
    private WebClient webClient;

    public Mono<Boolean> requestCheckAvailability(Order order) {
        String uri = "http://localhost:8082/api/v1/equipment/"
                + order.getEquipmentId() + "/schedule";
        return webClient.post()
                .uri(uri)
                .body(BodyInserters.fromValue(order))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> {
                            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_EQUIPMENT, order.getEquipmentId());
                        })
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> {
                    throw new CustomWebServiceException(ServiceLayerExceptionCodes.INTERNAL_SERVICE_ERROR);
                        })
                .bodyToMono(Boolean.class);
    }

    @Override
    public Mono<EquipmentDto> requestEquipmentInfo(Long equipmentId) {
        String uri = "http://localhost:8080/api/v1/equipment/" + equipmentId.toString()
                + "/info";
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> {
                            throw new CustomNotFoundException(ServiceLayerExceptionCodes.NOT_FOUND_EQUIPMENT, equipmentId);
                        })
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> {
                            throw new CustomWebServiceException(ServiceLayerExceptionCodes.INTERNAL_SERVICE_ERROR);
                        })
                .bodyToMono(EquipmentDto.class);

    }
}
