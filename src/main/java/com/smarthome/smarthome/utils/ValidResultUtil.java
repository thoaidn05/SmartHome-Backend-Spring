package com.smarthome.smarthome.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidResultUtil {
    static public List<String> getErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
    }
}
