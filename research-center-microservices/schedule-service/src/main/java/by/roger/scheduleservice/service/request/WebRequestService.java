package by.roger.scheduleservice.service.request;

import by.roger.scheduleservice.dto.EquipmentDto;
import by.roger.scheduleservice.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
@AllArgsConstructor
public class WebRequestService {
    private WebClient webClient;

    public Mono<EquipmentDto> getEquipmentByIdFromResearchCenterService(Long equipmentId){
        String uri = "http://localhost:8080/api/v1/equipment/" + equipmentId.toString()
                +"/for_schedule";
        return webClient.get()
                .uri(uri).retrieve().bodyToMono(EquipmentDto.class);

    }

    public Mono<List<Order>> getOrderListByEquipmentIdInPeriod(Long equipmentId, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startDateTime = startPeriod.format(formatter);
        String endDateTime = endPeriod.format(formatter);

        String uri = "http://localhost:8081/api/v1/equipment/" + equipmentId.toString() + "/orders?startTime=" +
                startDateTime +
                "&" +
                "endTime=" +
                endDateTime;
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Order.class)
                .collectList();
    }

}
