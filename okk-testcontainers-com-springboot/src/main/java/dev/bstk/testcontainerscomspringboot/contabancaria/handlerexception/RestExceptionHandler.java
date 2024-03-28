package dev.bstk.testcontainerscomspringboot.contabancaria.handlerexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ContaBancariaJaExisteException.class)
  public ResponseEntity<Object> invalido(final ContaBancariaJaExisteException exception) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new RestExceptionErroResponse(exception.getMessage()));
  }
}
