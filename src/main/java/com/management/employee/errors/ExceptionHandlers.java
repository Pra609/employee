package com.management.employee.errors;

import lombok.Value;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlers {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { NoSuchElementException.class })
    public ExceptionBody handleNoSuchElementException(Exception ex) {
        return new ExceptionBody(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { UserAlreadyExists.class })
    public ExceptionBody handleUserAlreadyExist(Exception ex) {
        return new ExceptionBody(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @Value
    static class ExceptionBody {
        String message;
        Integer status;
    }
}
