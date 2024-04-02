package org.com.contasapagar.component;

import jakarta.annotation.PostConstruct;
import org.com.contasapagar.model.Conta;
import org.com.contasapagar.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader {

    @Autowired
    private ContaRepository contaRepository;

    @PostConstruct
    public void init() {
        // Crie e salve algumas contas
        for (long i = 0; i < 5; i++) {
            Conta conta = new Conta();
            conta.setCpf("1234567890" + i);
            conta.setTitulo("Conta " + (i + 1));
            conta.setValor(100.0 * (i + 1));
            conta.setVencimento(LocalDateTime.now().plusDays(i + 1)); // Vencimento em dias incrementais
            conta.setTaxaDeJurosPorDiasDeAtraso(i+1);
            conta.setContraAtrasada('N');
            contaRepository.save(conta);
        }
    }
}
