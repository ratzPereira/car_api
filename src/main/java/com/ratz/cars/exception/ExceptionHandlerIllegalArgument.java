package com.ratz.cars.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerIllegalArgument {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity errorIllegalArgument(Exception ex) {
    return ResponseEntity.badRequest().build();
  }
}
