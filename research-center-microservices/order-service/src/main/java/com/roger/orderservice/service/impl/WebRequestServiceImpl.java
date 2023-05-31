package com.roger.orderservice.service.impl;

import com.roger.orderservice.dto.EquipmentDto;
import com.roger.orderservice.model.Order;
import com.roger.orderservice.service.WebRequestService;
import lombok.AllArgsConstructor;
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
                + order.getEquipmentId() +"/schedule";
        return webClient.post()
                .uri(uri)
                .body(BodyInserters.fromValue(order))
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Mono<EquipmentDto> requestEquipmentInfo(Long equipmentId) {
        String uri = "http://localhost:8080/api/v1/equipment/" + equipmentId.toString()
                    +"/info";
            return webClient.get()
                    .uri(uri).retrieve().bodyToMono(EquipmentDto.class);

    }
}
