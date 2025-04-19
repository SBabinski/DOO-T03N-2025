public class Cliente {
    private final String nome;
    private final int idade;

    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }



}

/*
Cadastro de clientes. (clientes podem ter mais de 1 veículo)
Cadastro de veículos (devem ser incluídos dois tipos de veículos, como carros e motos).
Buscar veículo por modelo, retornando o primeiro registro encontrado. (ex: Fiesta)
Buscar veículo por marca, retornando o primeiro registro encontrado. (ex: Ford)
Registrar entrada de um veículo para serviço.
Registrar saída de um veículo após finalização do serviço.
Verificar a disponibilidade de mecânicos para atender novos serviços.
Realizar a troca de óleo do veículo. (igual para carros e motos)
Realizar troca de uma peça. (diferente para carros e motos)
 */