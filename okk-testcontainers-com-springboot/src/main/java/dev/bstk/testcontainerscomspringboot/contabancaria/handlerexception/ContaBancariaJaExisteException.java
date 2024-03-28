package dev.bstk.testcontainerscomspringboot.contabancaria.handlerexception;

public class ContaBancariaJaExisteException extends RuntimeException {

  public ContaBancariaJaExisteException(final String message) {
    super(message);
  }
}
