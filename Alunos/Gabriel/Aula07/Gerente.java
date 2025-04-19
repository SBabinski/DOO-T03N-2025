package Alunos.Gabriel.Aula07;

public class Gerente extends Endereco{
    private String nome;
    private int idade;
    private String loja;
    private Double salarioBase;
    private Double salarioRecebido[];

    public Gerente(String nome, int idade, String loja,  Double
     salarioBase, String cidade, String bairro, String rua) {
        super(cidade, bairro, rua);
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new Double[]{1500.00, 2000.00, 2500.00};
    }
   

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getLoja() {
        return loja;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public Double[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setSalarioRecebido(Double[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }



    public void apresentarse(){
        System.out.println(
        "Nome:"+getNome()+", "+
        "Idade:"+getIdade()+", "+
        "Loja:"+getLoja()+".");
        
        
    }

    public void mediaSalario(){
        Double media = (this.salarioRecebido[0] + 
        this.salarioRecebido[1] + 
         this.salarioRecebido[2]) / 3;
        System.out.println("Média de Salário: " + media);
    }
    public void bonus(){
        
        System.out.println("Bônus é: " + (this.salarioBase * 0.35));
    }

    

    
}


