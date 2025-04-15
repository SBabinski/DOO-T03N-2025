public class Cliente {
    String nome;
    int idade;
    Endereco endereco;

    public Cliente(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        endereco.mostrarRua();
    }
}
