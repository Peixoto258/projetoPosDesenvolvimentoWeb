package org.com.contasapagar.service;

import org.com.contasapagar.dto.ContaDto;
import org.com.contasapagar.dto.NovoPagamentoDto;
import org.com.contasapagar.exception.ContaNotFoundException;
import org.com.contasapagar.mapper.ContaMapper;
import org.com.contasapagar.model.Conta;
import org.com.contasapagar.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContaService {


    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public ContaService(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    public List<ContaDto> listarContas() {
        return contaMapper.toListDto(contaRepository.findAll());
    }

    public ContaDto criarConta(Conta conta) {
        return contaMapper.toDto(contaRepository.save(conta));
    }

    public ContaDto atualizarConta(Long id, Conta conta) {
        if (contaRepository.existsById(id)) {
            conta.setId(id);
            return contaMapper.toDto(contaRepository.save(conta));
        } else {
            throw new ContaNotFoundException(id);
        }
    }

    public void deletarConta(Long id) {
        final var conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException(id));
        contaRepository.delete(conta);
    }

    public void atualizarValorComTaxaDeJuros(String dataEspecifica) {
        LocalDate dataFornecida = LocalDate.parse(dataEspecifica, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Conta> contas = contaRepository.findAll();
        for (Conta conta : contas) {
            if (conta.getVencimento().isBefore(dataFornecida)) {
                // Calcular o valor atualizado com base na taxa de juros por dias de atraso
                long diasAtraso = ChronoUnit.DAYS.between(conta.getVencimento(), dataFornecida);
                BigDecimal novoValor = conta.getValor()
                        .add(BigDecimal.valueOf(conta.getTaxaDeJurosPorDiasDeAtraso() * diasAtraso));
                conta.setValor(novoValor);
                contaRepository.save(conta);
            }
        }
    }

    public ContaDto encontrarPorId(Long id) {
        return contaRepository.findById(id)
                .map(contaMapper::toDto)
                .orElseThrow(() -> new ContaNotFoundException(id));
    }

    public ContaDto lancarPagamento(Long id, NovoPagamentoDto dto) {
        return contaRepository.findById(id)
                .map(contaEntity -> {
                    contaEntity.setDataPagamento(dto.dataPagamento());
                    return contaEntity;
                })
                .map(contaRepository::save)
                .map(contaMapper::toDto)
                .orElseThrow(() -> new ContaNotFoundException(id));
    }

}
