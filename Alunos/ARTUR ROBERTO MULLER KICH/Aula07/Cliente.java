public class Cliente extends Pessoa {
    Endereco endereco;

    public Cliente(String nome, int idade) {
        super(nome, idade);
        this.endereco = new Endereco("PR", "Cascavel", "Santa Felicidade", "Rua das Palmeiras", 45, "Casa amarela");
    }

    @Override
    public void apresentarse() {
        super.apresentarse();
        endereco.apresentarLogradouro();
    }
}

