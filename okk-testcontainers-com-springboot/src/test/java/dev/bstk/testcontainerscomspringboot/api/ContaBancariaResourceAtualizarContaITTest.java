package dev.bstk.testcontainerscomspringboot.api;

import dev.bstk.okkutil.json.jackson.Json;
import dev.bstk.testcontainerscomspringboot.AppTestContainer;
import dev.bstk.testcontainerscomspringboot.contabancaria.api.ContaBancariaRequest;
import dev.bstk.testcontainerscomspringboot.contabancaria.domain.ContaBancaria;
import dev.bstk.testcontainerscomspringboot.contabancaria.domain.ContaBancariaRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContaBancariaResourceAtualizarContaITTest extends AppTestContainer {

  @LocalServerPort
  private Integer portaHttp;

  @Autowired
  private ContaBancariaRepository contaBancariaRepository;

  @BeforeEach
  void setUp() {
    contaBancariaRepository.deleteAll();
  }

  @Test
  @DisplayName("Deve atualizar dados da conta bancária")
  void t1() {
    /// 1 - ARANGE
    final var contaBancariaJaExste = ContaBancaria.builder()
      .nome("Conta Nubank")
      .agencia("0001")
      .conta("4444-1")
      .banco("222")
      .gerente("Assunção")
      .observacao("Observações sobre a conta bancária")
      .build();

    contaBancariaRepository.saveAndFlush(contaBancariaJaExste);

    /// 1 - ARANGE - Crio a URL de Request com o Id da conta ja cadastrada que quero atualizar
    final var urlRequest = String.format("http://localhost:%s/v1/api/contas-bancarias/%s", portaHttp, contaBancariaJaExste.getId());

    /// 1 - ARANGE - Request com os novos dados
    final var contaBancariaComDadosAtualizadosRequest = ContaBancariaRequest.builder()
      .nome("Conta Bradesco")
      .agencia("2222")
      .conta("9999-1")
      .banco("382")
      .gerente("Maria Pereira")
      .observacao("Conta despezas da casa")
      .build();

    /// 2 - ACTION
    final var response = RestAssured
      .given()
      .header("Content-Type", "application/json")
      .and()
      .body(Json.toString(contaBancariaComDadosAtualizadosRequest))
      .when()
      .put(urlRequest)
      .then()
      .extract()
      .response();

    /// 3 - ASSERTS - Verifico se realmente atulizou os dados, pois a "response" tem que ser o mesmo que foi enviado na "request"
    Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(response.jsonPath().getString("nome")).isEqualTo(contaBancariaComDadosAtualizadosRequest.getNome());
    Assertions.assertThat(response.jsonPath().getString("agencia")).isEqualTo(contaBancariaComDadosAtualizadosRequest.getAgencia());
    Assertions.assertThat(response.jsonPath().getString("conta")).isEqualTo(contaBancariaComDadosAtualizadosRequest.getConta());
    Assertions.assertThat(response.jsonPath().getString("gerente")).isEqualTo(contaBancariaComDadosAtualizadosRequest.getGerente());
    Assertions.assertThat(response.jsonPath().getString("banco")).isEqualTo(contaBancariaComDadosAtualizadosRequest.getBanco());
    Assertions.assertThat(response.jsonPath().getString("observacao")).isEqualTo(contaBancariaComDadosAtualizadosRequest.getObservacao());

    /// 3 - ASSERTS - Verifico se tem apenas uma conta, afinal foi uma atualização e não um novo cadastro
    Assertions
      .assertThat(contaBancariaRepository.findAll())
      .hasSize(1);

    /// 3 - ASSERTS - Agora verifico se a conta que está no banco os dados sãpo iguais aos novos que pedi para atualizar
    contaBancariaRepository
      .findById(contaBancariaJaExste.getId())
      .ifPresent(resultado -> {
        Assertions.assertThat(contaBancariaComDadosAtualizadosRequest.getNome()).isEqualTo(resultado.getNome());
        Assertions.assertThat(contaBancariaComDadosAtualizadosRequest.getAgencia()).isEqualTo(resultado.getAgencia());
        Assertions.assertThat(contaBancariaComDadosAtualizadosRequest.getConta()).isEqualTo(resultado.getConta());
        Assertions.assertThat(contaBancariaComDadosAtualizadosRequest.getBanco()).isEqualTo(resultado.getBanco());
        Assertions.assertThat(contaBancariaComDadosAtualizadosRequest.getGerente()).isEqualTo(resultado.getGerente());
        Assertions.assertThat(contaBancariaComDadosAtualizadosRequest.getObservacao()).isEqualTo(resultado.getObservacao());
      });
  }
}
