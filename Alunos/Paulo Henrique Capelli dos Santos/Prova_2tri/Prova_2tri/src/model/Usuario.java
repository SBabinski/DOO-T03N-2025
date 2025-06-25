package model;

import com.google.gson.Gson;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane; // Necessário para exibir mensagens de erro/informação

/**
 * Representa um usuário do aplicativo, mantendo listas de séries favoritas,
 * assistidas e que deseja assistir. Inclui métodos para persistência de dados
 * (carregar e salvar) e para ordenar as listas de séries.
 */
public class Usuario {
    private String nome;
    private List<Serie> favoritas = new ArrayList<>();
    private List<Serie> assistidas = new ArrayList<>();
    private List<Serie> desejoAssistir = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    // Getters para as propriedades do usuário
    public String getNome() {
        return nome;
    }

    public List<Serie> getFavoritas() {
        return favoritas;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }

    /**
     * Carrega um usuário existente pelo nome ou cria um novo se não encontrado.
     * Os dados são armazenados em arquivos JSON na pasta "usuarios/".
     *
     * @param nome O nome do usuário a ser carregado ou criado.
     * @return O objeto Usuario carregado ou recém-criado.
     */
    public static Usuario carregarOuCriarUsuario(String nome) {
        Gson gson = new Gson();

        // Garante que a pasta 'usuarios' existe
        new File("usuarios/").mkdirs();
        File arquivo = new File("usuarios/" + nome + ".json");

        // Tenta carregar o usuário se o arquivo existir
        if (arquivo.exists()) {
            try {
                String json = new String(java.nio.file.Files.readAllBytes(arquivo.toPath()));
                return gson.fromJson(json, Usuario.class);
            } catch (Exception e) {
                // Em caso de erro ao carregar, informa o usuário e cria um novo
                JOptionPane.showMessageDialog(null, "Erro ao carregar usuário. Criando novo.");
                System.err.println("Erro ao carregar usuário: " + e.getMessage());
            }
        }

        // Cria um novo usuário se não foi possível carregar
        Usuario novoUsuario = new Usuario(nome);

        try {
            String json = gson.toJson(novoUsuario);
            java.nio.file.Files.write(arquivo.toPath(), json.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar novo usuário.");
            System.err.println("Erro ao salvar novo usuário: " + e.getMessage());
        }

        return novoUsuario;
    }

    /**
     * Adiciona uma série a uma das listas do usuário, dependendo do tipo especificado.
     * Garante que não haja séries duplicadas (com base no ID).
     * Após adicionar, salva as alterações.
     *
     * @param type O tipo de lista (FAVORITA, ASSISTIDA, DESEJO_ASSISTIR).
     * @param serie A série a ser adicionada.
     */
    public void adicionarSerie(TipoSerie type, Serie serie) {
        switch (type) {
            case FAVORITA:
                // Verifica se a série já existe na lista antes de adicionar
                if (favoritas.stream().noneMatch(s -> s.getId() == serie.getId())) {
                    this.favoritas.add(serie);
                }
                break;
            case ASSISTIDA:
                if (assistidas.stream().noneMatch(s -> s.getId() == serie.getId())) {
                    this.assistidas.add(serie);
                }
                break;
            case DESEJO_ASSISTIR:
                if (desejoAssistir.stream().noneMatch(s -> s.getId() == serie.getId())) {
                    this.desejoAssistir.add(serie);
                }
                break;
            default:
                break;
        }
        salvar(); // Salva as alterações após adicionar
    }

    /**
     * Remove uma série de uma das listas do usuário, dependendo do tipo especificado.
     * A remoção é baseada no ID da série.
     * Após remover, salva as alterações.
     *
     * @param type O tipo de lista (FAVORITA, ASSISTIDA, DESEJO_ASSISTIR).
     * @param serie A série a ser removida.
     */
    public void removerSerie(TipoSerie type, Serie serie) {
        switch (type) {
            case FAVORITA:
                this.favoritas.removeIf(s -> s.getId() == serie.getId());
                break;
            case ASSISTIDA:
                this.assistidas.removeIf(s -> s.getId() == serie.getId());
                break;
            case DESEJO_ASSISTIR:
                this.desejoAssistir.removeIf(s -> s.getId() == serie.getId());
                break;
            default:
                break;
        }
        salvar(); // Salva as alterações após remover
    }

    /**
     * Salva o estado atual do objeto Usuario em um arquivo JSON.
     */
    public void salvar() {
        try {
            Gson gson = new Gson();
            File arquivo = new File("usuarios/" + nome + ".json");
            String json = gson.toJson(this);
            // Escreve o JSON no arquivo usando UTF-8 para evitar problemas de codificação
            java.nio.file.Files.write(arquivo.toPath(), json.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // Em caso de erro ao salvar, informa o usuário
            JOptionPane.showMessageDialog(null, "Erro ao salvar alterações no usuário.");
            System.err.println("Erro ao salvar alterações no usuário: " + e.getMessage());
        }
    }

    // --- Métodos de Ordenação ---

    /**
     * Ordena a lista de séries favoritas do usuário.
     *
     * @param tipoOrdenacao O critério de ordenação ("alfabetica", "nota", "status", "dataEstreia").
     */
    public void ordenarFavoritas(String tipoOrdenacao) {
        ordenarLista(this.favoritas, tipoOrdenacao);
    }

    /**
     * Ordena a lista de séries assistidas do usuário.
     *
     * @param tipoOrdenacao O critério de ordenação ("alfabetica", "nota", "status", "dataEstreia").
     */
    public void ordenarAssistidas(String tipoOrdenacao) {
        ordenarLista(this.assistidas, tipoOrdenacao);
    }

    /**
     * Ordena a lista de séries que o usuário deseja assistir.
     *
     * @param tipoOrdenacao O critério de ordenação ("alfabetica", "nota", "status", "dataEstreia").
     */
    public void ordenarDesejoAssistir(String tipoOrdenacao) {
        ordenarLista(this.desejoAssistir, tipoOrdenacao);
    }

    /**
     * Método auxiliar genérico para ordenar uma lista de séries.
     *
     * @param lista A lista de séries a ser ordenada.
     * @param tipoOrdenacao O critério de ordenação ("alfabetica", "nota", "status", "dataEstreia").
     */
    private void ordenarLista(List<Serie> lista, String tipoOrdenacao) {
        switch (tipoOrdenacao) {
            case "alfabetica":
                // Ordena por nome da série (ignorando maiúsculas/minúsculas)
                Collections.sort(lista, (s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()));
                break;
            case "nota":
                // Ordena por nota geral (maior nota primeiro)
                Collections.sort(lista, (s1, s2) -> Double.compare(s2.getNota(), s1.getNota()));
                break;
            case "status":
                // Ordena por status da série (já concluída, ainda transmitindo, cancelada, etc.)
                Collections.sort(lista, (s1, s2) -> s1.getStatus().compareToIgnoreCase(s2.getStatus()));
                break;
            case "dataEstreia":
                // Ordena por data de estreia. Trata "Desconhecida" como uma data muito antiga
                // para garantir que séries com datas conhecidas apareçam antes.
                Collections.sort(lista, (s1, s2) -> {
                    try {
                        String data1 = s1.getDataEstreia().equals("Desconhecida") ? "0000-01-01" : s1.getDataEstreia();
                        String data2 = s2.getDataEstreia().equals("Desconhecida") ? "0000-01-01" : s2.getDataEstreia();
                        return data1.compareTo(data2);
                    } catch (Exception e) {
                        // Em caso de erro na data, não altera a ordem relativa
                        return 0;
                    }
                });
                break;
            default:
                // Nenhuma ordenação específica aplicada
                break;
        }
    }
}
