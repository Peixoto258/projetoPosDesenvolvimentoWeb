package org.com.contasapagar.controller;

import jakarta.validation.Valid;
import org.com.contasapagar.dto.ContaAtualizadaDto;
import org.com.contasapagar.dto.ContaDto;
import org.com.contasapagar.dto.NovaContaDto;
import org.com.contasapagar.dto.NovoPagamentoDto;
import org.com.contasapagar.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContaDto> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContaDto encontrarPorId(@PathVariable Long id) {
        return contaService.encontrarPorId(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ContaDto criarConta(@RequestBody @Valid NovaContaDto dto) {
        return contaService.criarConta(dto);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContaDto atualizarConta(@PathVariable Long id, @Valid @RequestBody ContaAtualizadaDto dto) {
        return contaService.atualizarConta(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable Long id) {
        contaService.deletarConta(id);
    }

    @PutMapping("/atualizar-valor/{data}")
    public void atualizarValorComTaxaDeJuros(@PathVariable String data) {
        contaService.atualizarValorComTaxaDeJuros(data);
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaDto> lancarPagamento(@PathVariable final Long id,
                                                    @RequestBody final NovoPagamentoDto novoPagamentoDto) {
        final var contaDto = contaService.lancarPagamento(id, novoPagamentoDto);
        return ResponseEntity.ok(contaDto);
    }

}
