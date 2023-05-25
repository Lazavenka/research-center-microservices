package by.roger.scheduleservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assistant {
    private long laboratoryId;
    private long assistantId;
    private String firstName;
    private String lastName;
    private String avatarFilePath;
}
