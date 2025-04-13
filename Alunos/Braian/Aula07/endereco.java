package Alunos.Braian.Aula07;

public class Endereco {
  private String estado;
  private String cidade;
  private String bairro;
  private Integer numeroRua;
  private String complemento;

  public Endereco(String estado, String cidade, String bairro, Integer numeroRua, String complemento) {
    this.estado = estado;
    this.cidade = cidade;
    this.bairro = bairro;
    this.numeroRua = numeroRua;
    this.complemento = complemento;
  }

  public String getEstado(){
    return estado;
  }
  public String getCidade(){
    return cidade;
  }
  public String getBairro(){
    return bairro;
  }
  public Integer getNumeroRua(){
    return numeroRua;
  }
  public String getComplemento(){
    return complemento;
  }

  public void setEstado(String estado){
    this.estado = estado;
  }
  public void setCidade(String cidade){
    this.cidade = cidade;
  }
  public void setBairro(String bairro){
    this.bairro = bairro;
  }
  public void setNumeroRua(Integer numeroRua){
    this.numeroRua = numeroRua;
  }
  public void setComplemento(String complemento){
    this.complemento = complemento;
  }

  public void apresentarLogradouro() {
    System.out.println("Endereço: Rua " + numeroRua + ", Bairro: " + bairro + ", Cidade: " + cidade + " - " + estado + ". " + complemento);
  }
}