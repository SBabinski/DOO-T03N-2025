package objetos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TVmazeAPI {

	// Endereço da api, após o ?q= vai o nome da serie escolhida
    private static final String API_URL = "https://api.tvmaze.com/search/shows?q=";
    // Aqui vai fazer as requisições para internet.
    private final OkHttpClient client;
    // traduz os dados JSON recebidos para objetos Java	
    private final Gson gson;

 
    public TVmazeAPI() {
    	
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }
    

    public List<Serie> buscarSeries(String nomeDaSerie) {
        try {
          
        	//Aqui vai montar uma URL de acordo com o que o usuário digitou para ser usado na internet.
            String nomeParaBusca = URLEncoder.encode(nomeDaSerie, StandardCharsets.UTF_8.toString());
            
            //junta a url fixa com a serie que quer buscar
            String urlCompleta = API_URL + nomeParaBusca;

           
            Request request = new Request.Builder()
                    .url(urlCompleta)
                    .build();

          //envia o pedido e salva a resposta da internet (response)
            try (Response response = client.newCall(request).execute()) {
            	

                if (!response.isSuccessful()) {
                	
                    System.err.println("Ocorreu um Erro na API: " + response.code() + " " + response.message());
                    
                    return new ArrayList<>();
                }

                //Se tiver conteudo, retorna, se não mostra uma mensagem de api vazia.
                ResponseBody body = response.body();
                
                if (body == null) {
                	
                    System.err.println("A resposta da API está vazia.");
                    return new ArrayList<>();
                    
                }

                //formata para String.java
                String json = body.string();
                
                //adiciona na lista a resposta da API.
                Type listType = new TypeToken<ArrayList<ApiResult>>() {}.getType();
                List<ApiResult> apiResults = gson.fromJson(json, listType);

             
                if (apiResults == null) {
                	
                    return new ArrayList<>();
                }
                
                return apiResults.stream() 
                		
                		//de acordo com cada resultado de api pega um show e no final colcoca em uma lista as informações da série.
                        .map(ApiResult::getShow) 
                        .filter(s -> s != null)                      
                        .collect(Collectors.toList()); 

            }
            
        
        } catch (IOException e) {
        	
        	//quando identificado um erro, é armazenado em e
            System.err.println("Erro de conexão ao tentar se comunicar com a API: " + e.getMessage());
            
            return new ArrayList<>(); 
        }
    }

   
  //guarda dados da serie 
    private static class ApiResult {
       
        private Serie show;

        public Serie getShow() {
        	
            return show;
        }
    }
}