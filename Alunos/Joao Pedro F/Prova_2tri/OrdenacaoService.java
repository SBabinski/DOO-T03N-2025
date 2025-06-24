package com.joaoedro.tvmaze;

import java.util.Comparator;
import java.util.List;

public class OrdenacaoService {
    
    /**
     * Ordena uma lista de séries por ordem alfabética do nome
     * @param series Lista de séries a ser ordenada
     */
    public void ordenarPorNome(List<Serie> series) {
        series.sort(Comparator.comparing(Serie::getNome));
    }
    
    /**
     * Ordena uma lista de séries por nota (decrescente)
     * @param series Lista de séries a ser ordenada
     */
    public void ordenarPorNota(List<Serie> series) {
        series.sort(Comparator.comparing(Serie::getNota).reversed());
    }
    
    /**
     * Ordena uma lista de séries por estado
     * Ordem: Em exibição, Finalizada, Cancelada
     * @param series Lista de séries a ser ordenada
     */
    public void ordenarPorEstado(List<Serie> series) {
        series.sort((s1, s2) -> {
            if (s1.getStatus() == null && s2.getStatus() == null) {
                return 0;
            }
            if (s1.getStatus() == null) {
                return 1;
            }
            if (s2.getStatus() == null) {
                return -1;
            }
            
            // Ordem personalizada: Em exibição primeiro, depois Finalizada, por último Cancelada
            int ordem1 = getOrdemStatus(s1.getStatus());
            int ordem2 = getOrdemStatus(s2.getStatus());
            
            return Integer.compare(ordem1, ordem2);
        });
    }
    
    /**
     * Ordena uma lista de séries por data de estreia (mais recente primeiro)
     * @param series Lista de séries a ser ordenada
     */
    public void ordenarPorDataEstreia(List<Serie> series) {
        series.sort((s1, s2) -> {
            if (s1.getDataEstreia() == null && s2.getDataEstreia() == null) {
                return 0;
            }
            if (s1.getDataEstreia() == null) {
                return 1;
            }
            if (s2.getDataEstreia() == null) {
                return -1;
            }
            
            // Ordem decrescente (mais recente primeiro)
            return s2.getDataEstreia().compareTo(s1.getDataEstreia());
        });
    }
    
    /**
     * Retorna um valor numérico para ordenação de status
     * @param status Status da série
     * @return Valor para ordenação
     */
    private int getOrdemStatus(StatusSerie status) {
        switch (status) {
            case EM_EXIBICAO:
                return 1;
            case FINALIZADA:
                return 2;
            case CANCELADA:
                return 3;
            default:
                return 4;
        }
    }
}
