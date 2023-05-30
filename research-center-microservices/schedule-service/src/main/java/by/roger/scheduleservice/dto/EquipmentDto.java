package by.roger.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentDto {
    private Long id;
    private String name;
    private String description;
    private String imageFilePath;
    private Long laboratoryId;
    private LocalTime averageResearchTime;
}
