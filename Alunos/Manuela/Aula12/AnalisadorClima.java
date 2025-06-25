package br.com.meuprojeto.tempo;

import org.json.JSONException;
import org.json.JSONObject;

public class AnalisadorClima {

    public static DadosClimaticos analisar(String respostaJson) {
        try {
            JSONObject raiz = new JSONObject(respostaJson);

            // Extrai o objeto de condições atuais ("currentConditions")
            JSONObject condicoesAtuais = raiz.getJSONObject("currentConditions");
            
            // Extrai o array de dias e pega o primeiro objeto (hoje)
            JSONObject dadosDeHoje = raiz.getJSONArray("days").getJSONObject(0);

            // Mapeia os valores do JSON para variáveis locais
            String localizacao = raiz.getString("resolvedAddress");
            double tempAtual = condicoesAtuais.getDouble("temp");
            double tempMax = dadosDeHoje.getDouble("tempmax");
            double tempMin = dadosDeHoje.getDouble("tempmin");
            double umidade = condicoesAtuais.getDouble("humidity");
            String condicoes = condicoesAtuais.getString("conditions");
            double precipitacao = condicoesAtuais.getDouble("precip");
            double velocidadeVento = condicoesAtuais.getDouble("windspeed");
            double direcaoVento = condicoesAtuais.getDouble("winddir");

            // Cria e retorna uma nova instância de DadosClimaticos
            return new DadosClimaticos(
                localizacao,
                tempAtual,
                tempMax,
                tempMin,
                umidade,
                condicoes,
                precipitacao,
                velocidadeVento,
                direcaoVento
            );

        } catch (JSONException e) {
            // Em caso de erro na análise do JSON, lança uma exceção mais clara
            throw new RuntimeException("Erro ao processar a resposta JSON da API.", e);
        }
    }
}