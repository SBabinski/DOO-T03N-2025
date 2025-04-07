package Aula04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Classe utilitária para converter LocalDate<->String a partir de um formato padrão (dd/MM/yyyy)
public class LocalDateUtil {
    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDateUtil() {} // construtor privado para impedir a classe de ser instanciada

    public static LocalDate stringParaLocalDate(String data) {
        return LocalDate.parse(data, formatador);
    }

    public static String localDateParaString(LocalDate data) {
        return data.format(formatador);
    }
}
