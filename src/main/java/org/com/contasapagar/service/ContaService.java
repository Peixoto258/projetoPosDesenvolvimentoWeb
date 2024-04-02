package org.com.contasapagar.service;

import org.com.contasapagar.model.Conta;
import org.com.contasapagar.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta atualizarConta(Long id, Conta conta) {
        if (contaRepository.existsById(id)) {
            conta.setId(id);
            return contaRepository.save(conta);
        } else {
            throw new RuntimeException("Conta não encontrada com o ID: " + id);
        }
    }

    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }

    public void atualizarValorComTaxaDeJuros(String dataEspecifica) {
        LocalDate dataFornecida = LocalDate.parse(dataEspecifica, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Conta> contas = contaRepository.findAll();
        for (Conta conta : contas) {
            if (conta.getVencimento().isBefore(dataFornecida.atStartOfDay())) {
                // Calcular o valor atualizado com base na taxa de juros por dias de atraso
                long diasAtraso = ChronoUnit.DAYS.between(conta.getVencimento(), dataFornecida);
                double novoValor = conta.getValor() + (conta.getTaxaDeJurosPorDiasDeAtraso() * diasAtraso);
                conta.setValor(novoValor);
                contaRepository.save(conta);
            }
        }
    }

    public Conta encontrarPorId(Long id) {
        return contaRepository.findById(id).get();
    }
}
