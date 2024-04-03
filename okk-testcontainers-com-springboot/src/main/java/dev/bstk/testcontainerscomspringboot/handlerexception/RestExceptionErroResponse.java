package dev.bstk.testcontainerscomspringboot.handlerexception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class RestExceptionErroResponse {

  @JsonProperty("erros")
  private final List<String> erros;

  @JsonProperty("dataHora")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private final LocalDateTime dataHora;

  public RestExceptionErroResponse(final List<String> erros) {
    this.erros = erros;
    this.dataHora = LocalDateTime.now();
  }

  public RestExceptionErroResponse(final String erro) {
    this.erros = List.of(erro);
    this.dataHora = LocalDateTime.now();
  }
}
