package Prova_1trim;

import java.util.*;

class Cliente {
    String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }
}

class Livro {
    String titulo;
    String autor;
    boolean emprestado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
    }

    public boolean isDisponivel() {
        return !emprestado;
    }
}

class LivroComum extends Livro {
    public LivroComum(String titulo, String autor) {
        super(titulo, autor);
    }
}

class LivroRaro extends Livro {
    String descricaoRaridade;

    public LivroRaro(String titulo, String autor, String descricaoRaridade) {
        super(titulo, autor);
        this.descricaoRaridade = descricaoRaridade;
    }

    @Override
    public boolean isDisponivel() {
        return false;
    }
}

class Biblioteca {
    List<Cliente> clientes = new ArrayList<>();
    List<Livro> livros = new ArrayList<>();
    Map<Livro, Date> emprestimos = new HashMap<>();

    public void cadastrarCliente(String nome) {
        clientes.add(new Cliente(nome));
    }

    public void cadastraLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        return livros.stream().filter(l -> l.titulo.equalsIgnoreCase(titulo)).findFirst().orElse(null);
    }

    public Livro buscarLivroPorAutor(String autor) {
        return livros.stream().filter(l -> l.autor.equalsIgnoreCase(autor)).findFirst().orElse(null);
    }

    public boolean emprestarLivro(Livro livro) {
        if (livro.isDisponivel()) {
            livro.emprestado = true;
            emprestimos.put(livro, new Date());
            return true;
        }
        return false;
    }

    public double devolverLivro(Livro livro) {
        Date dataEmprestimo = emprestimos.get(livro);
        if (dataEmprestimo != null) {
            long diasEmprestado = (new Date().getTime() - dataEmprestimo.getTime()) / (1000 * 60 * 60 * 24);
            livro.emprestado = false;
            emprestimos.remove(livro);

            if (diasEmprestado > 7) {
                return (diasEmprestado - 7) * 3.50;
            }
        }
        return 0.0;
    }
}

public class BibliotecaPub {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        biblioteca.cadastrarCliente("João");
        biblioteca.cadastraLivro(new LivroComum("A Arte da Guerra", "Sun Tzu"));
        biblioteca.cadastraLivro(
                new LivroRaro("A Ilha do Tesouro", "Robert Louis Stevenson", "Edição original de 1883"));

        while (true) {
            System.out.println("\n=== Sistema da Biblioteca ===");
            System.out.println("1. Buscar livro por título");
            System.out.println("2. Buscar livro por autor");
            System.out.println("3. Realizar empréstimo");
            System.out.println("4. Realizar devolução");
            System.out.println("5. Visualizar Livros");
            System.out.println("6. Sair");
            System.out.print("Selecione uma ação: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    Livro livroEncontrado = biblioteca.buscarLivroPorTitulo(titulo);
                    System.out.println(livroEncontrado != null ? "Livro encontrado: " + livroEncontrado.titulo
                            : "Não encontramos o livro.");
                    break;
                case 2:
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    Livro livroPorAutor = biblioteca.buscarLivroPorAutor(autor);
                    System.out.println(livroPorAutor != null ? "Livro encontrado: " + livroPorAutor.titulo
                            : "Não encontramos o livro.");
                    break;
                case 3:
                    System.out.print("Digite o título do livro para empréstimo: ");
                    String tituloEmprestimo = scanner.nextLine();
                    Livro livroEmprestar = biblioteca.buscarLivroPorTitulo(tituloEmprestimo);
                    if (livroEmprestar != null && biblioteca.emprestarLivro(livroEmprestar)) {
                        System.out.println("Livro emprestado com sucesso!");
                    } else {
                        System.out.println("Este livro não está disponível para empréstimo.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o título do livro para devolução: ");
                    String tituloDevolucao = scanner.nextLine();
                    Livro livroDevolver = biblioteca.buscarLivroPorTitulo(tituloDevolucao);
                    if (livroDevolver != null) {
                        double multa = biblioteca.devolverLivro(livroDevolver);
                        System.out.println("Devolução feita com sucesso. Valor da multa: R$ " + multa);
                    } else {
                        System.out.println("O livro não foi encontrado no sistema.");
                    }
                    break;
                case 5:
                    System.out.println("\n=== Livros Disponíveis ===");
                    for (Livro livro : biblioteca.livros) {
                        if (livro.isDisponivel()) {
                            System.out.println("- " + livro.titulo + " | Autor: " + livro.autor);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Sistema fechado. Até logo!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Por favor, escolha uma opção válida.");
            }
        }
    }
}