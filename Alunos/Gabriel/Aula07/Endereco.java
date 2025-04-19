package Alunos.Gabriel.Aula07;

public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;


    public Endereco(String estado, String cidade, String bairro,
    String rua, int numero, String complemento){

        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }


    public Endereco(String cidade, String bairro, String rua){
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;

    }


    
    public String getEstado() {
        return estado;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public String getBairro() {
        return bairro;
    }
    
    public String getRua() {
        return rua;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public String getComplemento() {
        return complemento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public void apresentarLogradouro(){
        System.out.println(
        "Estado:"+getEstado()+", "+
        "Cidade:"+getCidade()+", "+
        "Bairro:"+getBairro()+", "+
        "Rua:"+getRua()+", "+
        "Numero:"+getNumero()+", "+
        "Complemtento:"+getComplemento()+".");
    }
    
}

