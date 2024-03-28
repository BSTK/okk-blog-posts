package dev.bstk.testcontainerscomspringboot.contabancaria.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaBancariaResponse {
  private String nome;
  private String agencia;
  private String conta;
  private String gerente;
  private String banco;
  private String observacao;
}
