package com.gabrieldalmoro.controlefinanceiro.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResponse(
        int status,
        String mensagem,
        List<String> detalhes,
        LocalDateTime timestamp
) {}
