package com.rickdev.fiapFintech.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDto(Long id,

                           @NotBlank(message = "O tipo da transação é obrigatório")
                           @Size(max = 20, message = "O tipo da transação deve ter no máximo 20 caracteres")
                           String tipo,

                           @NotNull(message = "O valor da transação é obrigatório")
                           @Positive(message = "O valor da transação deve ser maior que zero")
                           BigDecimal valor,

                           @NotNull(message = "A data e hora da transação são obrigatórias")
                           LocalDateTime dataHora,

                           @NotNull(message = "O ID da conta é obrigatório")
                           Long idConta) {
}
