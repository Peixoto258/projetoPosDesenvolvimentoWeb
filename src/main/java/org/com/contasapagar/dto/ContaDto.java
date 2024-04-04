package org.com.contasapagar.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaDto(
        Long id,
        String cpf,
        String titulo,
        BigDecimal valor,
        BigDecimal valorAtualizadoComJuros,
        LocalDate vencimento,
        Boolean contraAtrasada,
        Long taxaDeJurosPorDiasDeAtraso,
        LocalDate dataPagamento
) { }
