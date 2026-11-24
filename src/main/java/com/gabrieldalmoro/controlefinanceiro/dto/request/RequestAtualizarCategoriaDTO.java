package com.gabrieldalmoro.controlefinanceiro.dto.request;

import com.gabrieldalmoro.controlefinanceiro.model.enums.StatusCategoria;
import com.gabrieldalmoro.controlefinanceiro.model.enums.TipoCategoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestAtualizarCategoriaDTO(

        @NotBlank(message = "O id é obrigatório!")
        Long id,

        @NotBlank(message = "A descricao é obrigatória!")
        @Size(max = 100, message = "A descricao deve ter no máximo 100 caracteres")
        String descricao,

        @NotNull(message = "O tipo da categoria é obrigatório!")
        TipoCategoria tipo,

        @NotBlank(message = "É necessário definir o status da categoria!")
        StatusCategoria status
    )
{}
