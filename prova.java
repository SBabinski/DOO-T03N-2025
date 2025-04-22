import java.util.*;

class Cliente {
    String nome;
    String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}

abstract class Livro {
    String titulo;
    String autor;
    boolean emprestado = false;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    abstract boolean podeSerEmprestado();
}

class LivroComum extends Livro {
    public LivroComum(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    boolean podeSerEmprestado() {
        return true;
    }
}

class LivroRaro extends Livro {
    String justificativa;

    public LivroRaro(String titulo, String autor, String justificativa) {
        super(titulo, autor);
        this.justificativa = justificativa;
    }

    @Override
    boolean podeSerEmprestado() {
        return false;
    }
}

class Emprestimo {
    Livro livro;
    Cliente cliente;
    Date dataEmprestimo;
    Date dataDevolucao;

    public Emprestimo(Livro livro, Cliente cliente) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = new Date();
    }

    public double devolver() {
        this.dataDevolucao = new Date();
        long diff = dataDevolucao.getTime() - dataEmprestimo.getTime();
        long dias = diff / (1000 * 60 * 60 * 24);
        livro.emprestado = false;
        if (dias > 7) {
            return (dias - 7) * 3.5;
        }
        return 0;
    }
}

class BibliotecaPub {
    List<Cliente> clientes = new ArrayList<>();
    List<Livro> livros = new ArrayList<>();
    List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarCliente(String nome, String cpf) {
        clientes.add(new Cliente(nome, cpf));
    }

    public void cadastrarLivroComum(String titulo, String autor) {
        livros.add(new LivroComum(titulo, autor));
    }

    public void cadastrarLivroRaro(String titulo, String autor, String justificativa) {
        livros.add(new LivroRaro(titulo, autor, justificativa));
    }

    public Livro buscarPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.titulo.equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public Livro buscarPorAutor(String autor) {
        return livros.stream()
                .filter(l -> l.autor.equalsIgnoreCase(autor))
                .findFirst()
                .orElse(null);
    }

    public boolean realizarEmprestimo(String titulo, String cpf) {
        Livro livro = buscarPorTitulo(titulo);
        Cliente cliente = clientes.stream().filter(c -> c.cpf.equals(cpf)).findFirst().orElse(null);

        if (livro != null && cliente != null && livro.podeSerEmprestado() && !livro.emprestado) {
            livro.emprestado = true;
            emprestimos.add(new Emprestimo(livro, cliente));
            return true;
        }
        return false;
    }

    public double realizarDevolucao(String titulo) {
        for (Emprestimo e : emprestimos) {
            if (e.livro.titulo.equalsIgnoreCase(titulo) && e.dataDevolucao == null) {
                return e.devolver();
            }
        }
        return -1; // Livro não encontrado ou já devolvido
    }

    public boolean verificarDisponibilidade(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        return livro != null && !livro.emprestado;
    }
}

public class Main {
    public static void main(String[] args) {
        BibliotecaPub biblioteca = new BibliotecaPub();

        biblioteca.cadastrarCliente("Matilde", "12345678900");
        biblioteca.cadastrarLivroComum("Cracking the Code Interview", "Gayle Laakmann");
        biblioteca.cadastrarLivroRaro("Livro Antigo", "Autor Desconhecido", "Primeira edição de 1800");

        boolean emprestado = biblioteca.realizarEmprestimo("Cracking the Code Interview", "12345678900");
        System.out.println("Empréstimo realizado: " + emprestado);

        double multa = biblioteca.realizarDevolucao("Cracking the Code Interview");
        System.out.println("Multa gerada: R$ " + multa);
    }
}
