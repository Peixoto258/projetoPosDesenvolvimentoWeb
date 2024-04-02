package org.com.contasapagar.controller;

import org.com.contasapagar.model.Conta;
import org.com.contasapagar.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
