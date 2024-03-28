package dev.bstk.testcontainerscomspringboot.api;


import dev.bstk.okkutil.json.jackson.Json;
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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContaBancariaResourceITTest {

  @Container
  private static final PostgreSQLContainer<?> POSTGRESQL_DB = new PostgreSQLContainer("postgres:14.1")
    .withDatabaseName("testcontainers-db")
    .withUsername("testcontainers-db")
    .withPassword("testcontainers-db");

  @DynamicPropertySource
  static void propertyConfig(final DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", POSTGRESQL_DB::getJdbcUrl);
    registry.add("spring.datasource.username", POSTGRESQL_DB::getUsername);
    registry.add("spring.datasource.password", POSTGRESQL_DB::getPassword);
    registry.add("spring.datasource.driverClassName", POSTGRESQL_DB::getDriverClassName);

    registry.add("spring.flyway.url", POSTGRESQL_DB::getJdbcUrl);
    registry.add("spring.flyway.user", POSTGRESQL_DB::getUsername);
    registry.add("spring.flyway.password", POSTGRESQL_DB::getPassword);
  }

  @LocalServerPort
  private Integer portaHttp;

  @Autowired
  private ContaBancariaRepository contaBancariaRepository;

  @BeforeEach
  void setUp() {
    contaBancariaRepository.deleteAll();
  }

  @Test
  @DisplayName("Deve cadastrar uma nova conta bancária")
  void t1() {
    /// 1 - ARANGE
    final var urlRequest = String.format("http://localhost:%s/v1/api/contas-bancarias", portaHttp);
    final var novaContaBancariaRequest = ContaBancariaRequest.builder()
      .nome("Conta Nubank")
      .agencia("0001")
      .conta("4444-1")
      .banco("222")
      .gerente("Assunção")
      .observacao("Observações sobre a conta bancária")
      .build();

    /// 2 - ACTION
    final  var response = RestAssured
      .given()
      .header("Content-Type", "application/json")
      .and()
      .body(Json.toString(novaContaBancariaRequest))
      .when()
      .post(urlRequest)
      .then()
      .extract()
      .response();

    /// 3 - ASSERTS
    Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    Assertions.assertThat(response.jsonPath().getString("nome")).isEqualTo(novaContaBancariaRequest.getNome());
    Assertions.assertThat(response.jsonPath().getString("agencia")).isEqualTo(novaContaBancariaRequest.getAgencia());
    Assertions.assertThat(response.jsonPath().getString("conta")).isEqualTo(novaContaBancariaRequest.getConta());
    Assertions.assertThat(response.jsonPath().getString("gerente")).isEqualTo(novaContaBancariaRequest.getGerente());
    Assertions.assertThat(response.jsonPath().getString("banco")).isEqualTo(novaContaBancariaRequest.getBanco());
    Assertions.assertThat(response.jsonPath().getString("observacao")).isEqualTo(novaContaBancariaRequest.getObservacao());

    Assertions
      .assertThat(contaBancariaRepository.findAll())
      .hasSize(1);
  }

  @Test
  @DisplayName("Deve lançar exception ao tentar cadastrar uma conta que ja existe")
  void t2() {
    /// 1 - ARANGE
    final var urlRequest = String.format("http://localhost:%s/v1/api/contas-bancarias", portaHttp);
    final var novaContaBancariaJaExste = ContaBancaria.builder()
      .nome("Conta Nubank")
      .agencia("0001")
      .conta("4444-1")
      .banco("222")
      .gerente("Assunção")
      .observacao("Observações sobre a conta bancária")
      .build();

    contaBancariaRepository.saveAndFlush(novaContaBancariaJaExste);

    final var novaContaBancariaRequest = """
      {
        "nome": "Conta Nubank",
        "agencia": "0001",
        "conta": "4444-1",
      	"banco": "222",
        "gerente": "Assunção",
        "observacao": "Observações sobre a conta bancária"
      }
      """;

    /// 2 - ACTION
    final  var response = RestAssured
      .given()
      .header("Content-Type", "application/json")
      .and()
      .body(novaContaBancariaRequest)
      .when()
      .post(urlRequest)
      .then()
      .extract()
      .response();

    /// 3 - ASSERTS
    Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    Assertions.assertThat(response.jsonPath().getString("dataHora")).isNotNull().isNotEmpty();
    Assertions.assertThat(response.jsonPath().getList("erros")).contains("Conta já cadastrada!");

    Assertions
      .assertThat(contaBancariaRepository.findAll())
      .hasSize(1);
  }
}
