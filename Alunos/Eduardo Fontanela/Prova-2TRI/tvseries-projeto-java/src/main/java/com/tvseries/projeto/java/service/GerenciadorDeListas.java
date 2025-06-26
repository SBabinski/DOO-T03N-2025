package com.tvseries.projeto.java.service;

import com.tvseries.projeto.java.model.Serie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeListas {

    private final String CAMINHO_DADOS = "dados";
    private final String ARQ_FAVORITOS = CAMINHO_DADOS + File.separator + "favoritos.json";
    private final String ARQ_ASSISTIDAS = CAMINHO_DADOS + File.separator + "assistidas.json";
    private final String ARQ_PARA_ASSISTIR = CAMINHO_DADOS + File.separator + "para_assistir.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> paraAssistir;

    public GerenciadorDeListas() {
        criarDiretorioDeDados();
        this.favoritos = carregarLista(ARQ_FAVORITOS);
        this.assistidas = carregarLista(ARQ_ASSISTIDAS);
        this.paraAssistir = carregarLista(ARQ_PARA_ASSISTIR);
    }

    private void criarDiretorioDeDados() {
        File diretorio = new File(CAMINHO_DADOS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void adicionarAFavoritos(Serie serie) { favoritos.add(serie); }
    public void removerDeFavoritos(int index) { favoritos.remove(index); }
    public void adicionarAAssistidas(Serie serie) { assistidas.add(serie); }
    public void removerDeAssistidas(int index) { assistidas.remove(index); }
    public void adicionarAParaAssistir(Serie serie) { paraAssistir.add(serie); }
    public void removerDeParaAssistir(int index) { paraAssistir.remove(index); }


    public List<Serie> getFavoritos() { return favoritos; }
    public List<Serie> getAssistidas() { return assistidas; }
    public List<Serie> getParaAssistir() { return paraAssistir; }

    public void salvarListas() {
        salvarLista(favoritos, ARQ_FAVORITOS);
        salvarLista(assistidas, ARQ_ASSISTIDAS);
        salvarLista(paraAssistir, ARQ_PARA_ASSISTIR);
    }

    private List<Serie> carregarLista(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type tipoLista = new TypeToken<ArrayList<Serie>>() {}.getType();
            List<Serie> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvarLista(List<Serie> lista, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista em " + caminhoArquivo + ": " + e.getMessage());
        }
    }
}