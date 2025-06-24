package com.joaoedro.tvmaze;


public enum StatusSerie {
    EM_EXIBICAO("Em exibição"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String descricao;

    StatusSerie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusSerie fromString(String status) {
        if (status == null) {
            return null;
        }
        
        switch (status.toLowerCase()) {
            case "running":
                return EM_EXIBICAO;
            case "ended":
                return FINALIZADA;
            case "canceled":
            case "cancelled":
                return CANCELADA;
            default:
                return null;
        }
    }
}
