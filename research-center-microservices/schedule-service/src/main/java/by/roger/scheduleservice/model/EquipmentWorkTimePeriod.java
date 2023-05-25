package by.roger.scheduleservice.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentWorkTimePeriod {
    private LocalDateTime startOfPeriod;
    private LocalDateTime endOfPeriod;
    private EquipmentAvailability availability;
    private final List<Assistant> availableAssistantIdsInPeriod = new ArrayList<>();

    public EquipmentWorkTimePeriod(LocalDateTime startOfPeriod,
                                   LocalDateTime endOfPeriod,
                                   EquipmentAvailability availability,
                                   List<Assistant> availableAssistantIdsInPeriod){
        this.startOfPeriod = startOfPeriod;
        this.endOfPeriod = endOfPeriod;
        this.availability = availability;
        this.availableAssistantIdsInPeriod.addAll(availableAssistantIdsInPeriod);

    }


    public Optional<Assistant> getAvailableAssistantInPeriod() {
        return availableAssistantIdsInPeriod.stream().findAny();
    }

    public boolean removeAssistantById(long assistantId) {
        return availableAssistantIdsInPeriod.removeIf(assistant -> assistant.getAssistantId() == assistantId);
    }

    public boolean containsStartDateTime(LocalDateTime startOfPeriod) {
        return this.startOfPeriod.isEqual(startOfPeriod) || (this.startOfPeriod.isBefore(startOfPeriod) && this.endOfPeriod.isAfter(startOfPeriod));
    }

    public boolean containsEndDateTime(LocalDateTime endOfPeriod) {
        return (this.startOfPeriod.isBefore(endOfPeriod) && this.endOfPeriod.isAfter(endOfPeriod)) || this.endOfPeriod.isEqual(endOfPeriod);
    }

    public boolean isInsideAnotherPeriod(EquipmentWorkTimePeriod another) {
        return this.startOfPeriod.isAfter(another.startOfPeriod) && this.endOfPeriod.isBefore(another.endOfPeriod);
    }

    public boolean crossPeriod(EquipmentWorkTimePeriod another) {
        boolean crossing = this.isInsideAnotherPeriod(another);
        if (this.containsStartDateTime(another.startOfPeriod)) {
            crossing = true;
        }
        if (this.containsEndDateTime(another.endOfPeriod)) {
            crossing = true;
        }
        return crossing;
    }

}
