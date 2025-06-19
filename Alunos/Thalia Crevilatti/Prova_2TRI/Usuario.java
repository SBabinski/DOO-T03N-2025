import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Classe que representa um usuário do sistema de gerenciamento de séries

public class Usuario {
    private String nome;
    private List<Serie> assistidas;
    private List<Serie> paraAssistir;
    private List<Serie> favoritas;

    public void ordenarFavoritas() {
        favoritas = favoritas.stream()
                .sorted((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()))
                .collect(Collectors.toList());
    }

    public void ordenarAssistidas() {
        assistidas = assistidas.stream()
                .sorted((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()))
                .collect(Collectors.toList());
    }

    public void ordenarParaAssistir() {
        paraAssistir = paraAssistir.stream()
                .sorted((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()))
                .collect(Collectors.toList());
    }

    public Usuario() {
        this.nome = "";
        this.favoritas = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.paraAssistir = new ArrayList<>();
    }
    
    public Usuario(String nome) {
        this.nome = nome;
        this.favoritas = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.paraAssistir = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Serie> getFavoritas() { return favoritas; }
    public List<Serie> getAssistidas() { return assistidas; }
    public List<Serie> getParaAssistir() { return paraAssistir; }

    public void adicionarSerie(List<Serie> lista, Serie serie) {
        if (!lista.contains(serie)) {
            lista.add(serie);
            System.out.println("Série adicionada com sucesso!");
        } else {
            System.out.println("Essa série já está na lista.");
        }
    }

    public void removerSerie(List<Serie> lista, Serie serie) {
        if (lista.remove(serie)) {
            System.out.println("Série removida com sucesso!");
        } else {
            System.out.println("Série não encontrada na lista.");
        }
    }

    public void exibirLista(List<Serie> lista) {
        if (lista.isEmpty()) {
            System.out.println("Lista vazia.");
        } else {
            for (Serie serie : lista) {
                System.out.println(serie);
                System.out.println("---------------");
            }
        }
    }

    public void adicionarFavorita(Serie serie) {
        adicionarSerie(favoritas, serie);
    }

    public void adicionarAssistida(Serie serie) {
        adicionarSerie(assistidas, serie);
    }

    public void adicionarParaAssistir(Serie serie) {
        adicionarSerie(paraAssistir, serie);
    }

    public void adicionarSerieNaLista(int lista, Serie serie) {
        switch (lista) {
            case 1 -> adicionarFavorita(serie);
            case 2 -> adicionarAssistida(serie);
            case 3 -> adicionarParaAssistir(serie);
            default -> System.out.println("Lista inválida.");
        }
    }

    public void removerSerieDaLista(int lista, Serie serie) {
        switch (lista) {
            case 1 -> removerSerie(favoritas, serie);
            case 2 -> removerSerie(assistidas, serie);
            case 3 -> removerSerie(paraAssistir, serie);
            default -> System.out.println("Lista inválida.");
        }
    }

    public void ordenarListas(Scanner scanner) {
        System.out.println("Escolha a lista para ordenar: 1.Favoritas 2.Assistidas 3.Para assistir");
        int lista = Integer.parseInt(scanner.nextLine());
        System.out.println("Escolha o critério: 1.Nome 2.Nota 3.Status 4.Data de Estreia");
        int criterio = Integer.parseInt(scanner.nextLine());

        List<Serie> listaEscolhida;

        switch (lista) {
            case 1 -> listaEscolhida = favoritas;
            case 2 -> listaEscolhida = assistidas;
            case 3 -> listaEscolhida = paraAssistir;
            default -> {
                System.out.println("Lista inválida.");
                return;
            }
        }

        switch (criterio) {
            case 1 -> listaEscolhida.sort((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()));
            case 2 -> listaEscolhida.sort((s1, s2) -> Double.compare(s2.getNota(), s1.getNota())); // decrescente
            case 3 -> listaEscolhida.sort((s1, s2) -> s1.getStatus().compareToIgnoreCase(s2.getStatus()));
            case 4 -> listaEscolhida.sort((s1, s2) -> {
                try {
                    LocalDate date1 = s1.getDataEstreia().isEmpty() ? LocalDate.MIN : LocalDate.parse(s1.getDataEstreia());
                    LocalDate date2 = s2.getDataEstreia().isEmpty() ? LocalDate.MIN : LocalDate.parse(s2.getDataEstreia());
                    return date1.compareTo(date2);
                } catch (DateTimeParseException e) {
                    return 0; // Tratar erro de parsing, ou considerar séries sem data no final, etc.
                }
            });
            default -> {
                System.out.println("Critério inválido.");
                return;
            }
        }

        System.out.println("Lista ordenada:");
        exibirLista(listaEscolhida);
    }

    @Override
    public String toString() {
        return "Usuário: " + nome +
               "\nSéries favoritas: " + favoritas.size() +
               "\nSéries assistidas: " + assistidas.size() +
               "\nSéries para assistir: " + paraAssistir.size();
    }

    public void exibirTodasAsListas() {
        System.out.println("\n--- Listas de Séries ---");
        System.out.println("Favoritas:");
        exibirLista(favoritas);
        System.out.println("\nAssistidas:");
        exibirLista(assistidas);
        System.out.println("\nPara Assistir:");
        exibirLista(paraAssistir);
    }
}