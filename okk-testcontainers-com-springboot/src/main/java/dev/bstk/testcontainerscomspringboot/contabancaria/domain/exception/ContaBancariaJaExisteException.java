package dev.bstk.testcontainerscomspringboot.contabancaria.domain.exception;

public class ContaBancariaJaExisteException extends RuntimeException {

  public ContaBancariaJaExisteException(final String message) {
    super(message);
  }
}
