package com.gabrieldalmoro.controlefinanceiro.dto.response;

import com.gabrieldalmoro.controlefinanceiro.model.enums.StatusUsuario;

public record ResponseUsuarioDTO(
        Long id,
        String nome,
        String email,
        StatusUsuario status)
{}
