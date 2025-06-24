package ArquivoJSON;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import Objetos.Serie;
import Objetos.Usuario;

public class JSONService {
	
	static final JSONObject objeto = new JSONObject();

    public static boolean writeJson(Usuario user) { 
    	//Obejeto a ser escrito
    	JSONObject objeto = new JSONObject();
    	
    	//Add usuario
    	objeto.put("usuario", user.getName());
    	
    	//add listas
    	JSONArray listaFav = new JSONArray();
    	listaFav = criaObjetoFav(user.getFavoritos());
    	
    	JSONArray listaAssistir = new JSONArray();
        listaAssistir = criaObjetoAssistir(user.getAssistir());
        
        JSONArray listaVistos = new JSONArray();
        listaVistos = criaObjetoVistos(user.getVistos());
        
        objeto.put("favoritos", listaFav);
        objeto.put("assistir", listaAssistir);
        objeto.put("vistos", listaVistos);
        
        //escrever json
		 try (FileWriter file = new FileWriter(user.getName()+".json")) {
	            file.write(objeto.toString(4));
	            file.flush();
	            System.out.println("Usuario salvo com sucesso");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return false;
	}
    
    private static JSONArray criaObjetoFav(List<Serie> list) {
    	JSONArray series = new JSONArray();
		    for (Serie serie : list) {
		        JSONObject programa = new JSONObject();
		        programa.put("id", serie.getId());
		        programa.put("nome", serie.getNome());
		        programa.put("lingua", serie.getLingua());
		        programa.put("genero", serie.getGenero());
		        programa.put("nota", serie.getNota());
		        programa.put("estado", serie.getEstado());
		        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        String data = dtf.format(serie.getDataInicio());
		        programa.put("dataInicio", data);
		        programa.put("dataFim", serie.getDataFim());
		        programa.put("emisor", serie.getCanal());
		        series.put(programa);
		    }
	    return series;
    }
    
    private static JSONArray criaObjetoAssistir(List<Serie> list) {
    	JSONArray series = new JSONArray();
		    for (Serie serie : list) {
		        JSONObject programa = new JSONObject();
		        programa.put("id", serie.getId());
		        programa.put("nome", serie.getNome());
		        programa.put("lingua", serie.getLingua());
		        programa.put("genero", serie.getGenero());
		        programa.put("nota", serie.getNota());
		        programa.put("estado", serie.getEstado());
		        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        String data = dtf.format(serie.getDataInicio());
		        programa.put("dataInicio", data);
		        programa.put("dataFim", serie.getDataFim());
		        programa.put("emisor", serie.getCanal());
		        series.put(programa);
		    }
	    return series;
    }
    
    private static JSONArray criaObjetoVistos(List<Serie> list) {
    	JSONArray series = new JSONArray();
		    for (Serie serie : list) {
		        JSONObject programa = new JSONObject();
		        programa.put("id", serie.getId());
		        programa.put("nome", serie.getNome());
		        programa.put("lingua", serie.getLingua());
		        programa.put("genero", serie.getGenero());
		        programa.put("nota", serie.getNota());
		        programa.put("estado", serie.getEstado());
		        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        String data = dtf.format(serie.getDataInicio());
		        programa.put("dataInicio", data);
		        programa.put("dataFim", serie.getDataFim());
		        programa.put("emisor", serie.getCanal());
		        series.put(programa);
		    }
	    return series;
    }

    public static Usuario lerJSON(String user) {
        try {
            // Caminho do arquivo JSON
            String caminhoArquivo = user+".json";

            // Lê o conteúdo do arquivo como uma String
            String conteudo = Files.readString(Paths.get(caminhoArquivo));

            // Cria um JSONObject a partir do conteúdo lido
            JSONObject jsonObject = new JSONObject(conteudo);

            // Acessa os dados do JSON
            String usuario = jsonObject.getString("usuario");
            
            //criar listas para armazenar as do json
            List<Serie> favoritos = new ArrayList<Serie>();
            List<Serie> assistir = new ArrayList<Serie>();
            List<Serie> vistos = new ArrayList<Serie>();
            
            //add nas listas
            JSONArray fav = jsonObject.getJSONArray("favoritos");
            favoritos = lerLista(fav);
            
            JSONArray assisti = jsonObject.getJSONArray("assistir");
            assistir = lerLista(assisti);
            
            JSONArray visto = jsonObject.getJSONArray("vistos");
            vistos = lerLista(visto);
            
            Usuario cliente = new Usuario();
            cliente.setName(usuario);
            cliente.setFavoritos(favoritos);
            cliente.setAssistir(assistir);
            cliente.setVistos(vistos);
            
            return cliente;

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }
    
    public static List<Serie> lerLista( JSONArray favo) {
    	
    	List<Serie> programa = new ArrayList<Serie>();
    
    	for (int i = 0; i < favo.length(); i++) {
    		JSONObject fav = favo.getJSONObject(i);
	    	Integer id = fav.getInt("id");
	    	String nome = fav.getString("nome");
			String lingua = fav.getString("lingua");
			JSONArray genero = fav.getJSONArray("genero");
			Double nota = fav.getDouble("nota");
			String estado = fav.getString("estado");
			String data = fav.getString("dataInicio");
			LocalDate dataInicio = LocalDate.parse(data);
			String dataFim = fav.getString("dataFim");
			String canal = fav.getString("emisor");
			
			programa.add(new Serie(id, nome, lingua, genero, nota, dataInicio, dataFim, canal, estado));
		}
		return programa;
    }
	
}
