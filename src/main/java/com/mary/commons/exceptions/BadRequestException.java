package com.mary.commons.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException{
    public BadRequestException(Map<String, List<String>> messages, HttpStatus status) {
        super(messages, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, HttpStatus status) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
