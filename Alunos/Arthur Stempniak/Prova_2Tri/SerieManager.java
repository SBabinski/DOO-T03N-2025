import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SerieManager {

    public enum Ordenacao {
        NOME,
        NOTA,
        ESTADO,
        DATA_ESTREIA
    }

    public List<Serie> ordenarLista(List<Serie> series, Ordenacao criterio) {
        List<Serie> sortedList = new ArrayList<>(series);
        switch (criterio) {
            case NOME:
                sortedList.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER));
                break;
            case NOTA:
                sortedList.sort(Comparator.comparingDouble(Serie::getNotaGeral).reversed());
                break;
            case ESTADO:
                sortedList.sort(Comparator.comparing(Serie::getEstado, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)));
                break;
            case DATA_ESTREIA:
                sortedList.sort(Comparator.comparing(this::parseData, Comparator.nullsLast(Date::compareTo)));
                break;
        }
        return sortedList;
    }

    private Date parseData(Serie s) {
        if (s.getDataEstreia() == null || s.getDataEstreia().isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(s.getDataEstreia());
        } catch (ParseException e) {
            return null;
        }
    }

    public void removerSeriePorNome(List<Serie> lista, String nome) {
        lista.removeIf(s -> s.getNome().equalsIgnoreCase(nome));
    }

    public boolean contemSerie(List<Serie> lista, String nome) {
        return lista.stream().anyMatch(s -> s.getNome().equalsIgnoreCase(nome));
    }
}