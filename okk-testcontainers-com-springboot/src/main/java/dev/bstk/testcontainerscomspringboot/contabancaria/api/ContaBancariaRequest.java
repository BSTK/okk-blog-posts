package dev.bstk.testcontainerscomspringboot.contabancaria.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContaBancariaRequest {

  @NotBlank(message = "Campo nome não pode ser nulo/vazio")
  private String nome;

  @NotBlank(message = "Campo agência não pode ser nulo/vazio")
  private String agencia;

  @NotBlank(message = "Campo conta não pode ser nulo/vazio")
  private String conta;

  @NotBlank(message = "Campo banco não pode ser nulo/vazio")
  private String banco;

  private String gerente;
  private String observacao;
}
