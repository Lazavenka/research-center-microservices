package com.roger.scheduleservice.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InputFieldValidator {
    public boolean isCorrectId(Long id){
        return id>0;
    }
    public boolean isDateTimePast(LocalDateTime dateTime){
        return dateTime.toLocalDate().isBefore(LocalDateTime.now().toLocalDate());
    }
}
