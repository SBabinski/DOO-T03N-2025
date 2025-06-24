package Servicos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import Objetos.Serie;
import Objetos.Usuario;


public class Requisicao {
	
	private static Scanner scan = new Scanner(System.in);
	Usuario usuario;
    static ArrayList<Serie> favoritos = null;
    static ArrayList<Serie> assistir = null;
    static ArrayList<Serie> vistos = null;
	
	//Mostrar serie & criar listas
		public static void MostrarPrograma() throws Exception {
				try {
					Serie programa = criarSerie();
					System.out.println(programa);
					try {
						System.out.println("deseja adicior a uma lista \n"
								+ "1 - sim \n"
								+ "2 - nao \n");
						Integer resp = scan.nextInt();
							if(resp == 1) {
								System.out.println("Em qual lista deseja adicionar \n"
										+ "1 - lista de Favoritos \n"
										+ "2 - lista de Desejo ver mais tarde \n"
										+ "3 - lista de ja assistidos \n");
								Integer ope = scan.nextInt();
									switch (ope) {
									case 1: {
										Listas.addFavoritos(programa);
										break;
									}
									case 2: {
										Listas.addAssistir(programa);
										break;
									}
									case 3: {
										Listas.addVistos(programa);
										break;
									}
									default:
										System.out.println("opcao inexistente");
									}
							}
					} catch (Exception e) {
						System.out.println("Nao é possivel ler caracter");
					}
				} catch (Exception e) {
					System.out.println("nao foi possivel encontrar esse programa");
				}
			}
			
			
	//Conecta com Api
		public static JSONObject ChamarApi(String serie) throws Exception{
			
			HttpClient cliente = HttpClient.newHttpClient();
			URI url = new URI("https://api.tvmaze.com/singlesearch/shows?q=" + serie);
			HttpRequest request = HttpRequest.newBuilder(url)
					.GET()
					.build();
			HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
			
			JSONObject resposta = new JSONObject(response.body());
			if(response.statusCode() == 200) {
				return resposta;
			}else {
			return null;
			}
	}

		
	//cria variavel Serei
		public static Serie criarSerie() throws Exception{
			
				System.out.println("Digite o nome do programa");
				String nomeSerie = scan.next();
				JSONObject serie = ChamarApi(nomeSerie);
				String nome = serie.getString("name");
				String lingua = serie.getString("language");
				JSONArray genero = serie.getJSONArray("genres");
				Double nota;
				try {
					JSONObject notas = serie.getJSONObject("rating");
					 nota = notas.getDouble("average");
				} catch (Exception e) {
					 nota = 0.0;
				}
				String data = serie.getString("premiered");
				LocalDate dataInicio = LocalDate.parse(data);
				String dataFim = serie.getString("ended");
				String canal;
				try {
					JSONObject canais = serie.getJSONObject("network");
					canal = canais.getString("name");
				}catch(Exception e) {
						try {
							JSONObject canais = serie.getJSONObject("webChannel");
							canal = canais.getString("name");
						} catch(Exception e1) {
							canal = null;
							}	
					}
				String estado = serie.getString("status");

				
				Serie programa = new Serie(nome, lingua, genero, nota, dataInicio, dataFim, canal, estado); 
				
				return programa;
		}
		
		
}
