package dev.bstk.testcontainerscomspringboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@ExtendWith(AppTestExtension.class)
public abstract class AppTestContainer {

  private static final PostgreSQLContainer<?> POSTGRESQL_DB = new PostgreSQLContainer<>("postgres:14.1")
    .withDatabaseName("testcontainers-db")
    .withUsername("testcontainers-db")
    .withPassword("testcontainers-db")
    .withReuse(true);
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

  public static void startContainer() {
    POSTGRESQL_DB.start();
    log.info("\n\n");
    log.info("***********************************************");
    log.info("**** INICIANDO O CONTAINER : POSTGRESQL_DB ****");
    log.info("***********************************************");
    log.info("\n\n");
  }

  public static void stopContainer() {
    POSTGRESQL_DB.stop();
    log.info("\n\n");
    log.info("*************************************************");
    log.info("**** FINALIZANDO O CONTAINER : POSTGRESQL_DB ****");
    log.info("*************************************************");
    log.info("\n\n");
  }
}
