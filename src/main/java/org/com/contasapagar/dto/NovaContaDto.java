package org.com.contasapagar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovaContaDto(
        @NotBlank String cpf,
        @NotBlank String titulo,
        @NotNull BigDecimal valor,
        @NotNull LocalDate vencimento,
        @NotNull Long taxaDeJurosPorDiasDeAtraso
) { }
