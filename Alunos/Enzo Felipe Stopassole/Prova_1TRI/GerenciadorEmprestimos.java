import java.util.ArrayList;
import java.util.List;

class GerenciadorEmprestimos {
    private List<Emprestimo> historico = new ArrayList<>();

    public void registrarEmprestimo(Emprestimo emprestimo) {
        historico.add(emprestimo);
    }

    public Emprestimo localizarEmprestimoAtivo(Livro livro) {
        return historico.stream()
            .filter(e -> e.getLivro().equals(livro) && !e.estaDevolvido())
            .findFirst()
            .orElse(null);
    }
}