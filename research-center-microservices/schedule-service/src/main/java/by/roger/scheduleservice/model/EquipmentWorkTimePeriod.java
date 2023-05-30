package by.roger.scheduleservice.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EquipmentWorkTimePeriod {
    private LocalDateTime startOfPeriod;
    private LocalDateTime endOfPeriod;
    private EquipmentAvailability availability;

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
