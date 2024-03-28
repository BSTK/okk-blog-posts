package dev.bstk.testcontainerscomspringboot.contabancaria.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

  @Query(value = """
    SELECT  CASE WHEN COUNT(cb) > 0 THEN true ELSE false END
    FROM  ContaBancaria cb
    WHERE cb.agencia = :agencia
    AND   cb.conta   = :conta
    AND   cb.banco   = :banco
    """)
  boolean existeContaBancariaCadastrada(
    @Param("agencia") final String agencia,
    @Param("conta") final String conta,
    @Param("banco") final String banco
  );
}
