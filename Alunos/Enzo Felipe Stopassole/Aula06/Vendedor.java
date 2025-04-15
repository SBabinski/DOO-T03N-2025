import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    // Atributos
    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private List<Double> salarioRecebido;
    private List<String> mesesSalario;

    public Vendedor(String string){

    }
    // Construtor
    public Vendedor(String nome, int idade, String loja, String cidade, 
                   String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        inicializarSalarios();
    }

    // Método privado para inicialização
    private void inicializarSalarios() {
        this.salarioRecebido = new ArrayList<>();
        this.mesesSalario = new ArrayList<>();
        
        // Adiciona meses e salários correspondentes
        String[] meses = {"Janeiro", "Fevereiro", "Março"};
        for (String mes : meses) {
            mesesSalario.add(mes);
        }
        
        salarioRecebido.add(salarioBase);
        salarioRecebido.add(salarioBase * 0.9); 
        salarioRecebido.add(salarioBase * 1.1);
    }

    // Métodos públicos
    public void apresentar() {
        System.out.println("\n--- Informações do Vendedor ---");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    public double calcularMediaSalarial() {
        if (salarioRecebido.isEmpty()) return 0.0;
        
        return salarioRecebido.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }

    public String apresentarVendedor() {
        return "";
    }

    public String calcularMedia() {
        return "";
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getLoja() { return loja; }
    public void setLoja(String loja) { this.loja = loja; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    public List<Double> getSalarioRecebido() { return salarioRecebido; }
    public List<String> getMesesSalario() { return mesesSalario; }
}