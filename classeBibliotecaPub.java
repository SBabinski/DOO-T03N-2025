import java.util.*;

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
