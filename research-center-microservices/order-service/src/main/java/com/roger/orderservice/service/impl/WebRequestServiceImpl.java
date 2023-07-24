package com.roger.orderservice.service.impl;

import com.roger.researchcenter.dto.EquipmentDto;
import com.roger.researchcenter.exception.CustomNotFoundException;
import com.roger.researchcenter.exception.CustomWebServiceException;
import com.roger.researchcenter.exception.ServiceLayerExceptionCodes;
import com.roger.orderservice.model.Order;
import com.roger.orderservice.service.WebRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@AllArgsConstructor
public class WebRequestServiceImpl implements WebRequestService {
    private WebClient.Builder webClient;

    public boolean requestCheckAvailability(Order order) {
        String uri = "http://schedule-service/api/v1/schedule/equipment/" + order.getEquipmentId();
        return Boolean.TRUE.equals(webClient.build().post()
                // todo inter-service communication .header(HttpHeaders.AUTHORIZATION, ???)
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
                .bodyToMono(Boolean.class)
                .block());
    }

    @Override
    public EquipmentDto requestEquipmentInfo(Long equipmentId) {
        String uri = "http://research-center-service/api/v1/equipment/" + equipmentId.toString()
                + "/info";
        return webClient.build().get()
                // todo inter-service communication .header(HttpHeaders.AUTHORIZATION, ???)
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
                .bodyToMono(EquipmentDto.class)
                .block();

    }
}
