package model;

import java.util.List;

public class Serie {
    private String nome;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String status;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;
    private String descricao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public List<String> getGeneros() { return generos; }
    public void setGeneros(List<String> generos) { this.generos = generos; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDataEstreia() { return dataEstreia; }
    public void setDataEstreia(String dataEstreia) { this.dataEstreia = dataEstreia; }

    public String getDataTermino() { return dataTermino; }
    public void setDataTermino(String dataTermino) { this.dataTermino = dataTermino; }

    public String getEmissora() { return emissora; }
    public void setEmissora(String emissora) { this.emissora = emissora; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "📺 Nome: " + nome +
                "\n🌐 Idioma: " + idioma +
                "\n🎭 Generos: " + String.join(", ", generos) +
                "\n⭐ Nota geral: " + nota +
                "\n📶 Estado: " + status +
                "\n📅 Estreia: " + (dataEstreia != null ? dataEstreia : "Nao disponível") +
                "\n📅 Término: " + (dataTermino != null ? dataTermino : "Ainda em exibicao") +
                "\n🏢 Emissora: " + (emissora != null ? emissora : "Nao disponível") +
                "\n📖 Descricao: " + descricao;
    }
}
