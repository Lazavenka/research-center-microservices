package by.roger.scheduleservice.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InputFieldValidator {
    public boolean isCorrectId(Long id){
        return id>0;
    }
    public boolean isDateTimeNotPast(LocalDateTime dateTime){
        return dateTime.isAfter(LocalDateTime.now());
    }
}
