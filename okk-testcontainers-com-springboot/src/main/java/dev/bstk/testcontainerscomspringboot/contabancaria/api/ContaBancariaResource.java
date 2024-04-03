package dev.bstk.testcontainerscomspringboot.contabancaria.api;

import dev.bstk.okkutil.mapper.modelmapper.Mapper;
import dev.bstk.testcontainerscomspringboot.contabancaria.domain.ContaBancaria;
import dev.bstk.testcontainerscomspringboot.contabancaria.domain.ContaBancariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/contas-bancarias")
public class ContaBancariaResource {

  private final ContaBancariaService contaBancariaService;

  @PostMapping
  public ResponseEntity<ContaBancariaResponse> novaContaBancaria(@RequestBody @Valid final ContaBancariaRequest request) {
    final var contaBancaria = Mapper.to(request, ContaBancaria.class);
    final var contaBancariaCadastrada = contaBancariaService.novaContaBancaria(contaBancaria);
    final var contaBancariaCadastradaResponse = Mapper.to(contaBancariaCadastrada, ContaBancariaResponse.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(contaBancariaCadastradaResponse);
  }

  @PutMapping("/{contaBancariaId}")
  public ResponseEntity<ContaBancariaResponse> atualizarContaBancaria(@PathVariable("contaBancariaId") final Long contaBancariaId,
                                                                      @RequestBody @Valid final ContaBancariaRequest request) {
    final var contaBancaria = Mapper.to(request, ContaBancaria.class);
    final var contaBancariaAtualizada = contaBancariaService.atualizarContaBancaria(contaBancariaId, contaBancaria);
    final var contaBancariaAtualizadaResponse = Mapper.to(contaBancariaAtualizada, ContaBancariaResponse.class);

    return ResponseEntity.ok(contaBancariaAtualizadaResponse);
  }
}
