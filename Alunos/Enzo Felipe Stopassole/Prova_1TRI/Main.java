import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SistemaBiblioteca biblioteca = new SistemaBiblioteca();

        biblioteca.adicionarObra(new LivroComum("Código Limpo", "Robert C. Martin"));
        biblioteca.adicionarObra(new LivroRaro("Dom Casmurro 1ª Edição", "Machado de Assis", "Edição original de 1899"));

        Cliente cliente1 = new Cliente("João Silva", "CLI-001");
        biblioteca.cadastrarUsuario(cliente1);
        Livro livro = biblioteca.pesquisarPorTitulo("Código Limpo");
        try {
            if (livro != null) {
                biblioteca.executarEmprestimo(cliente1, livro);
                System.out.println("Empréstimo realizado!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        Livro livroDevolucao = biblioteca.pesquisarPorTitulo("Código Limpo");
        if (livroDevolucao != null) {
            double multa = biblioteca.processarDevolucao(livroDevolucao);
            System.out.println("Multa: R$ " + multa);
        }
    }
}

class SistemaBiblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionarObra(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarUsuario(Cliente cliente) {
        clientes.add(cliente);
    }

    public Livro pesquisarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void executarEmprestimo(Cliente cliente, Livro livro) {
        System.out.println("Empréstimo executado para " + cliente + " do livro " + livro.getTitulo());
    }

    public double processarDevolucao(Livro livro) {
        return 10.0;
    }
}

class Cliente {
    private String nome;
    private String id;

    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getId() {
        return id;
    }
}

class Livro {
    private String titulo;
    private String autor;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}

class LivroComum extends Livro {
    public LivroComum(String titulo, String autor) {
        super(titulo, autor);
    }
}

class LivroRaro extends Livro {
    public LivroRaro(String titulo, String autor, String edicao) {
        super(titulo, autor);
    }
}