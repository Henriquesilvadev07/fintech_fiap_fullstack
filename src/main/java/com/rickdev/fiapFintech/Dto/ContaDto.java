package com.rickdev.fiapFintech.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ContaDto(Long id,

                       @NotBlank(message = "A agência é obrigatória")
                       @Size(max = 10, message = "A agência deve ter no máximo 10 caracteres")
                       String agencia,

                       @NotBlank(message = "O número da conta é obrigatório")
                       @Size(max = 20, message = "O número da conta deve ter no máximo 20 caracteres")
                       String numeroConta,

                       @NotNull(message = "O saldo é obrigatório")
                       BigDecimal saldo,

                       @NotNull(message = "O cliente é obrigatório")
                       Long idCliente) {
}
