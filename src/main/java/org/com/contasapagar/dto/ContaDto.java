package org.com.contasapagar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaDto(
        Long id,
        String cpf,
        String titulo,
        BigDecimal valor,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        BigDecimal valorAtualizadoComJuros,
        LocalDate vencimento,
        Boolean contraAtrasada,
        Long taxaDeJurosPorDiasDeAtraso,
        LocalDate dataPagamento
) { }
