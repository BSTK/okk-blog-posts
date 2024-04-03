package dev.bstk.testcontainerscomspringboot.contabancaria.domain;

import dev.bstk.testcontainerscomspringboot.contabancaria.domain.exception.ContaBancariaJaExisteException;
import dev.bstk.testcontainerscomspringboot.handlerexception.RecursoNaoEncontradoException;
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

  public ContaBancaria atualizarContaBancaria(final Long contaBancariaId,
                                              final ContaBancaria contaBancaria) {
    final var contaBancariaAtualizar = contaBancariaRepository
      .findById(contaBancariaId)
      .orElseThrow(() -> {
        log.warn("Não existe conta cadastrada para id [ {} ]", contaBancariaId);
        return new RecursoNaoEncontradoException(
          String.format("Não existe conta cadastrada para id [ %s ]", contaBancariaId)
        );
      });

    contaBancariaAtualizar.setNome(contaBancaria.getNome());
    contaBancariaAtualizar.setAgencia(contaBancaria.getAgencia());
    contaBancariaAtualizar.setConta(contaBancaria.getConta());
    contaBancariaAtualizar.setBanco(contaBancaria.getBanco());
    contaBancariaAtualizar.setGerente(contaBancaria.getGerente());
    contaBancariaAtualizar.setObservacao(contaBancaria.getObservacao());

    return contaBancariaRepository.saveAndFlush(contaBancariaAtualizar);
  }
}
