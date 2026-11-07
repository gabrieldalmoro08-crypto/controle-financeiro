package com.gabrieldalmoro.controlefinanceiro.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestAtualizacaoUsuarioDTO (

        @NotBlank(message = "O nome é obrigatório!")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres!")
        String nome,

        @Email(message = "E-mail inválido!")
        @NotBlank(message = "O e-mail é obrigatório!")
        @Size(max = 254, message = "O e-mail deve ter no máximo 254 caracteres")
        String email
    )

{}
