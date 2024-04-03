package dev.bstk.testcontainerscomspringboot.handlerexception;

public class RecursoNaoEncontradoException extends RuntimeException {

  public RecursoNaoEncontradoException(final String message) {
    super(message);
  }
}