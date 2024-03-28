package dev.bstk.testcontainerscomspringboot.contabancaria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTA_BANCARIA")
public class ContaBancaria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotEmpty
  @Column(name = "NOME")
  private String nome;

  @NotNull
  @NotEmpty
  @Column(name = "AGENCIA")
  private String agencia;

  @NotNull
  @NotEmpty
  @Column(name = "CONTA")
  private String conta;

  @NotNull
  @NotEmpty
  @Column(name = "BANCO")
  private String banco;

  @Column(name = "GERENTE")
  private String gerente;

  @Column(name = "OBSERVACAO")
  private String observacao;

  @CreationTimestamp
  @Column(name = "DATA_INSERT")
  protected Instant dataInsert;

  @UpdateTimestamp
  @Column(name = "DATA_UPDATE")
  protected Instant dataUpdate;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContaBancaria that = (ContaBancaria) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
