package Aula07;

public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;
    private String complemento;

    public Endereco(String estado, String cidade, String bairro, String rua, Integer numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        String enderecoFormatado = String.format(
                "%s, %s%s%n%s - %s/%s",
                rua,
                numero,
                (complemento != null && !complemento.isBlank()) ? " " + complemento : "",
                bairro,
                cidade,
                estado
        ); // padrão de endereço dos Correios

        System.out.println(enderecoFormatado);
    }
}
