package model;

import java.time.LocalDate;
import java.util.List;

public record ShowDTO(
        int id,
        String name,
        String language,
        List<String> genres,
        double rating,
        String status,
        LocalDate premiered,
        LocalDate ended,
        String broadcaster) {
}
