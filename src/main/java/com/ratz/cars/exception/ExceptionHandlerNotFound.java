package com.ratz.cars.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerNotFound {

  @ExceptionHandler(EmptyResultDataAccessException.class)

  public ResponseEntity errorNotFound(Exception ex) {
    return ResponseEntity.notFound().build();
  }
}
