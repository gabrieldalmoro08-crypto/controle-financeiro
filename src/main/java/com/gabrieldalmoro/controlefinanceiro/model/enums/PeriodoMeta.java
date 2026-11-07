package com.gabrieldalmoro.controlefinanceiro.model.enums;

public enum PeriodoMeta {
    MENSAL(1),
    TRIMESTRAL(3),
    SEMESTRAL(6),
    ANUAL(12);

    private final int meses;

    PeriodoMeta(int meses) {
        this.meses = meses;
    }

    public int getMeses() {
        return meses;
    }
}