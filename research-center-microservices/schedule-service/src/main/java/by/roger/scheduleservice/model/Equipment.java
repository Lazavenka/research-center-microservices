package by.roger.scheduleservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment {
    private String id;
    private String name;
    private String description;
    private String imageFilePath;
    private boolean isNeedAssistant;
    private BigDecimal pricePerHour;
    private LocalTime averageResearchTime;
}
