public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String complemento;

    public Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereço: " + cidade + "/" + estado + ", " + bairro + 
                         ", " + numero + " - " + complemento);
    }

    // Getters (opcional)
    public String getEstado() { return estado; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}