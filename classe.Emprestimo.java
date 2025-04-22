import java.util.*;

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
