package org.com.contasapagar.controller;

import org.com.contasapagar.dto.ContaDto;
import org.com.contasapagar.dto.NovoPagamentoDto;
import org.com.contasapagar.model.Conta;
import org.com.contasapagar.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/{id}")
    public Conta encontrarPorId(@PathVariable Long id) {
        return contaService.encontrarPorId(id);
    }

    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        return contaService.criarConta(conta);
    }

    @PutMapping("/{id}")
    public Conta atualizarConta(@PathVariable Long id, @RequestBody Conta conta) {
        return contaService.atualizarConta(id, conta);
    }

    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable Long id) {
        contaService.deletarConta(id);
    }

    @PutMapping("/atualizar-valor/{data}")
    public void atualizarValorComTaxaDeJuros(@PathVariable String data) {
        contaService.atualizarValorComTaxaDeJuros(data);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContaDto> lancarPagamento(@PathVariable final Long id,
                                                    @RequestBody final NovoPagamentoDto novoPagamentoDto) {
        final var contaDto = contaService.lancarPagamento(id, novoPagamentoDto);
        return ResponseEntity.ok(contaDto);
    }
}
