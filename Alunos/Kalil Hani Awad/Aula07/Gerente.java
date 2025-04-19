public class Gerente extends Endereco  {

    private Double salarioBase;
    private Double salarioRecebido[] = {5000.0, 6000.0, 7000.0};

    public Gerente(String estado, String cidade, String bairro, String numero, String complemento, String nome, int idade, String rua, Double salarioBase, Double[] salarioRecebido) {
        super(estado, cidade, bairro, numero, complemento);
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public Double calcularMedia(){
        Double soma = 0.0;
        for(Double S : salarioRecebido){
            soma = S;
        }
        return soma/salarioRecebido.length;
    }
    public Double calcularBonus(){
        return salarioBase * 0.35;
    }
    public String apresentarse(String enderecoLoja) {
        return "Gerente da loja localizada em: " + enderecoLoja;
    }
}

