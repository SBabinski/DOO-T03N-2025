import java.util.ArrayList;
import java.util.List;

public class Usuario {
    public String nome;

    public List<Serie> favoritas;
    public List<Serie> assistidas;
    public List<Serie> desejadas;

    public Usuario(String nome) {
        this.nome = nome;

        favoritas = new ArrayList<>();
        assistidas = new ArrayList<>();
        desejadas = new ArrayList<>();
    }
}