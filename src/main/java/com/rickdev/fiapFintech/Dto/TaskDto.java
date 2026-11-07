package com.rickdev.fiapFintech.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskDto(@NotBlank(message = "O titulo é obrigatorio") String titulo,
                      @NotBlank(message = "A descricao é obrigatoria") String descricao,
                      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataCriacao) {
}
