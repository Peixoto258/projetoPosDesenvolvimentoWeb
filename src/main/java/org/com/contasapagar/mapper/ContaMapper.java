package org.com.contasapagar.mapper;

import org.com.contasapagar.dto.ContaDto;
import org.com.contasapagar.model.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {

    public ContaDto toDto(final Conta conta) {
        return new ContaDto(
                conta.getId(),
                conta.getCpf(),
                conta.getTitulo(),
                conta.getValor(),
                conta.getVencimento(),
                conta.getTaxaDeJurosPorDiasDeAtraso(),
                conta.getDataPagamento());
    }

}
