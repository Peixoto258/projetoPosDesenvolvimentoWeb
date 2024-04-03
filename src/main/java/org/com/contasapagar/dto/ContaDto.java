package org.com.contasapagar.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContaDto(
        Long id,
        String cpf,
        String titulo,
        Double valor,
        LocalDateTime vencimento,
        Long taxaDeJurosPorDiasDeAtraso,
        LocalDate dataPagamento
) { }
