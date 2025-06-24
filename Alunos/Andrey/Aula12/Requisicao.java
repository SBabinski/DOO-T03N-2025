package Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import Objeto.Clima;


public class Requisicao {
				
		private static Scanner scan = new Scanner(System.in);
	
		//Conecta com Api
			public static JSONObject ChamarApi(String cidade) throws Exception{
				
				HttpClient cliente = HttpClient.newHttpClient();
				URI url = new URI("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+ cidade +"?key=HLGQRHNQVH2B2RJCBD7DASP8W");
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
			public static Clima criarClima() throws Exception{
				
					System.out.println("Digite o nome da cidade");
					String cidade = scan.next();
					BigDecimal tempMaxima = null;
					BigDecimal tempMinima = null;
					BigDecimal humidade = null;
					String condicaoTempo = null;
					BigDecimal precipitacao = null;
					BigDecimal velocidadeAr = null;
					
					JSONObject previsao = ChamarApi(cidade);
						JSONArray day = previsao.getJSONArray("days");
						for (int i = 0; i < day.length(); i++) {
				            JSONObject dayObj = day.getJSONObject(i);
				            tempMaxima = dayObj.getBigDecimal("tempmax");
							tempMinima = dayObj.getBigDecimal("tempmin");
							humidade = dayObj.getBigDecimal("humidity");
							condicaoTempo = dayObj.optString("conditions"); 
							precipitacao = dayObj.getBigDecimal("precip");
							velocidadeAr = dayObj.getBigDecimal("windspeed");
				        }
					
					
					Clima clima = new Clima(cidade, tempMaxima, tempMinima, humidade, condicaoTempo, precipitacao, velocidadeAr); 
					 
					return clima;
			}
			
			
}
