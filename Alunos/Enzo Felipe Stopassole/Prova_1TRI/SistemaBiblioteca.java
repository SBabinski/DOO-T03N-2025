import java.util.ArrayList;
import java.util.List;

public class SistemaBiblioteca {

// Placeholder for Livro class
class Livro {
    private String titulo;
    private boolean emprestado;

    public Livro(String titulo) {
        this.titulo = titulo;
        this.emprestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
}

class Cliente {
    private List<Livro> livrosEmprestados = new ArrayList<>();

    public double adicionarLivroEmprestado(Livro livro) {
        livrosEmprestados.add(livro);
        return 0.0; 
    }
}
    private List<Livro> obras = new ArrayList<>();
    private List<Cliente> usuarios = new ArrayList<>();

    public void adicionarObra(Livro livro) {
        obras.add(livro);
    }

    public void cadastrarUsuario(Cliente cliente) {
        usuarios.add(cliente);
    }

    public Livro pesquisarPorTitulo(String titulo) {
        for (Livro livro : obras) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void executarEmprestimo(Cliente cliente, Livro livro) throws Exception {
        if (livro.isEmprestado()) {
            throw new Exception("Livro já emprestado.");
        }
        livro.setEmprestado(true);
        cliente.adicionarLivroEmprestado(livro);
    }

    public double processarDevolucao(Livro livro) {
        livro.setEmprestado(false);
        return 0.0; 
    }

}