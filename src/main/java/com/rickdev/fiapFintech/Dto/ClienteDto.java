package com.rickdev.fiapFintech.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClienteDto(Long id,

                         @NotBlank(message = "O nome é obrigatório")
                         @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
                         String nome,

                         @NotBlank(message = "O e-mail é obrigatório")
                         @Email(message = "E-mail inválido")
                         @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
                         String email,

                         @NotBlank(message = "O CPF é obrigatório")
                         @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 caracteres")
                         String cpf,

                         @NotNull(message = "A data de nascimento é obrigatória")
                         LocalDate dataNascimento) {
}
