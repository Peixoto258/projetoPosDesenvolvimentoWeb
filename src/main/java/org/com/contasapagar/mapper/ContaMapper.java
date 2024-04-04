package org.com.contasapagar.mapper;

import org.com.contasapagar.dto.ContaDto;
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
                conta.getValorAtualizadoComJuros(),
                conta.getVencimento(),
                conta.isContraAtrasada(),
                conta.getTaxaDeJurosPorDiasDeAtraso(),
                conta.getDataPagamento());
    }

}
