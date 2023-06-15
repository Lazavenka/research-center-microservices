package com.roger.scheduleservice.validator;

public class InputFieldValidator {
    private InputFieldValidator() {
    }

    public static boolean isCorrectId(Long id) {
        return id > 0;
    }
}
