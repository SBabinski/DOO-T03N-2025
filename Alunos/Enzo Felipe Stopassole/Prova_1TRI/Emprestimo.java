import java.time.LocalDate;

public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public class Cliente {
        private String nome;

        public Cliente(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    public class Livro {
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

    public void registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
    }

    public double calcularMulta() {
        if (dataDevolucao == null) return 0.0;
        
        long diasAtraso = dataDevolucao.toEpochDay() - 
                         dataEmprestimo.plusDays(7).toEpochDay();
        return Math.max(diasAtraso, 0) * 3.50;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean estaDevolvido() {
        return dataDevolucao != null;
    }
}