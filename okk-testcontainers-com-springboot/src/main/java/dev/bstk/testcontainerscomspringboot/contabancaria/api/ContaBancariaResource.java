package dev.bstk.testcontainerscomspringboot.contabancaria.api;

import dev.bstk.okkutil.mapper.modelmapper.Mapper;
import dev.bstk.testcontainerscomspringboot.contabancaria.domain.ContaBancaria;
import dev.bstk.testcontainerscomspringboot.contabancaria.domain.ContaBancariaRepository;
import dev.bstk.testcontainerscomspringboot.contabancaria.domain.ContaBancariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/contas-bancarias")
public class ContaBancariaResource {

  private final ContaBancariaService contaBancariaService;
  private final ContaBancariaRepository contaBancariaRepository;

  @PostMapping
  public ResponseEntity<ContaBancariaResponse> novaContaBancaria(@RequestBody @Valid final ContaBancariaRequest request) {
    final var contaBancaria = Mapper.to(request, ContaBancaria.class);
    final var contaBancariaCadastrada = contaBancariaService.novaContaBancaria(contaBancaria);
    final var contaBancariaCadastradaResponse = Mapper.to(contaBancariaCadastrada, ContaBancariaResponse.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(contaBancariaCadastradaResponse);
  }

  @GetMapping
  public ResponseEntity<List<ContaBancariaResponse>> get() {
    final var contaBancarias = contaBancariaRepository.findAll();
    final var contaBancariasResponse = Mapper.list(contaBancarias, ContaBancariaResponse.class);

    return ResponseEntity.ok(contaBancariasResponse);
  }
}
