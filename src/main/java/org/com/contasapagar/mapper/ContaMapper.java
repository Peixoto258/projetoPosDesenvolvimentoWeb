package org.com.contasapagar.mapper;

import org.com.contasapagar.dto.ContaAtualizadaDto;
import org.com.contasapagar.dto.ContaDto;
import org.com.contasapagar.dto.NovaContaDto;
import org.com.contasapagar.model.Conta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContaMapper {

    public List<ContaDto> toListDto(List<Conta> contas) {
        return contas.stream()
                .map(this::toDto)
                .toList();
    }

    public ContaDto toDto(final Conta conta) {
        return new ContaDto(
                conta.getId(),
                conta.getCpf(),
                conta.getTitulo(),
                conta.getValor(),
                conta.isContraAtrasada() ? conta.getValorAtualizadoComJuros() : null,
                conta.getVencimento(),
                conta.isContraAtrasada(),
                conta.getTaxaDeJurosPorDiasDeAtraso(),
                conta.getDataPagamento());
    }

    public Conta toModel(final NovaContaDto dto) {
        return new Conta(dto.cpf(),
                dto.titulo(),
                dto.valor(),
                dto.vencimento(),
                dto.taxaDeJurosPorDiasDeAtraso());
    }


    public Conta toModelUpdate(final ContaAtualizadaDto dto, Conta conta) {
        conta.setCpf(dto.cpf());
        conta.setTitulo(dto.titulo());
        conta.setValor(dto.valor());
        conta.setVencimento(dto.vencimento());
        conta.setTaxaDeJurosPorDiasDeAtraso(dto.taxaDeJurosPorDiasDeAtraso());
        return conta;
    }

}
