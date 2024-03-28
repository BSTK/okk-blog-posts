package dev.bstk.testcontainerscomspringboot.contabancaria.domain;

import dev.bstk.testcontainerscomspringboot.contabancaria.handlerexception.ContaBancariaJaExisteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContaBancariaService {

  private final ContaBancariaRepository contaBancariaRepository;

  public ContaBancaria novaContaBancaria(final ContaBancaria contaBancaria) {
    final var existeContaBancariaCadastrada = contaBancariaRepository.existeContaBancariaCadastrada(
      contaBancaria.getAgencia(),
      contaBancaria.getConta(),
      contaBancaria.getBanco()
    );

    if (existeContaBancariaCadastrada) {
      throw new ContaBancariaJaExisteException("Conta já cadastrada!");
    }

    return contaBancariaRepository.saveAndFlush(contaBancaria);
  }
}
