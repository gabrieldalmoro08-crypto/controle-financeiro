package com.gabrieldalmoro.controlefinanceiro.dto.response;

import com.gabrieldalmoro.controlefinanceiro.model.enums.StatusCategoria;
import com.gabrieldalmoro.controlefinanceiro.model.enums.TipoCategoria;

public record ResponseCategoriaDTO(
        Long id,
        String descricao,
        TipoCategoria tipo,
        StatusCategoria status
) {}