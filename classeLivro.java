import java.util.*;

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
