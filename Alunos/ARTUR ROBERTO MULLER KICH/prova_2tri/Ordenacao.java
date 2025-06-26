package org.example.util;

import org.example.model.Serie;

import java.util.Comparator;
import java.util.List;

public class Ordenacao {
    
    public enum TipoOrdenacao {
        ALFABETICA("Nome (A-Z)"),
        NOTA("Nota (Maior-Menor)"),
        ESTADO("Estado"),
        DATA_ESTREIA("Data de Estreia (Mais recente)");
        
        private final String descricao;
        
        TipoOrdenacao(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
        
        @Override
        public String toString() {
            return descricao;
        }
    }
    
    public static void ordenarSeries(List<Serie> series, TipoOrdenacao tipo) {
        switch (tipo) {
            case ALFABETICA:
                series.sort(Comparator.comparing(Serie::getName, Comparator.nullsLast(String::compareToIgnoreCase)));
                break;
            case NOTA:
                series.sort(Comparator.comparing(Serie::getNotaMedia, 
                        Comparator.nullsLast(Comparator.reverseOrder())));
                break;
            case ESTADO:
                series.sort(Comparator.comparing(Serie::getStatus, 
                        Comparator.nullsLast(String::compareToIgnoreCase)));
                break;
            case DATA_ESTREIA:
                series.sort(Comparator.comparing(Serie::getPremiered, 
                        Comparator.nullsLast(Comparator.reverseOrder())));
                break;
        }
    }
}
