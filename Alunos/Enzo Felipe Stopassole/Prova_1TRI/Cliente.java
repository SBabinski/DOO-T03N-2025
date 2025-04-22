
class Cliente {
    private String nome;
    private String identificacao;

    public Cliente(String nome, String identificacao) {
        this.nome = nome;
        this.identificacao = identificacao;
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }
}