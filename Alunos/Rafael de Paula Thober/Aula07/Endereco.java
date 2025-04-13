public class Endereco {
  String estado;
  String cidade;
  String bairro;
  String rua;
  String numero;
  String complemento;

  public void apresentarLogradouro() {
      System.out.println(rua + ", " + numero + " - " + bairro + ", " + cidade + " (" + estado + ")");
      if (complemento != null && !complemento.isEmpty()) {
          System.out.println("Complemento: " + complemento);
      }
  }
}
