package by.roger.scheduleservice.service.request;

import by.roger.scheduleservice.model.Assistant;
import by.roger.scheduleservice.model.Equipment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@AllArgsConstructor
public class WebRequestService {
    private WebClient webClient;

    public Mono<Equipment> getEquipmentByIdFromResearchCenterService(Long equipmentId){
        return webClient.get()
                .uri("get from research center equipment by id").retrieve().bodyToMono(Equipment.class);

    }

    public Mono<List<Assistant>> getAssistantsByLaboratoryIdFromResearchCenterService(Long laboratoryId) {
    }
}
