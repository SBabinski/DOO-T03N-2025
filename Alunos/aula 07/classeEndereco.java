import java.util.*;
import java.text.SimpleDateFormat;

class Endereco {
    String estado, cidade, bairro, rua, numero, complemento;

    public Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println(rua + ", " + numero + ", " + bairro + ", " + cidade + ", " + estado + " - " + complemento);
    }
}