package br.com.meuprojeto.tempo;

public record DadosClimaticos(
	    String localizacao,
	    double temperaturaAtual,
	    double temperaturaMaxima,
	    double temperaturaMinima,
	    double umidade,
	    String condicoes,
	    double precipitacao,
	    double velocidadeVento,
	    double direcaoVento
	) {
	    // Sobrescrevemos o método toString() para criar uma exibição formatada e amigável.
	    @Override
	    public String toString() {
	        return """
	               ----------------------------------------------------
	               Previsão do Tempo para: %s
	               ----------------------------------------------------
	               Temperatura Atual: %.1f°C
	               Temperaturas do Dia: Mín. %.1f°C / Máx. %.1f°C
	               Umidade do Ar: %.1f%%
	               Condições: %s
	               Precipitação: %.1f mm
	               Vento: %.1f km/h na direção %s°
	               ----------------------------------------------------
	               """.formatted(
	                   localizacao, 
	                   temperaturaAtual, 
	                   temperaturaMinima, 
	                   temperaturaMaxima, 
	                   umidade, 
	                   condicoes, 
	                   precipitacao, 
	                   velocidadeVento, 
	                   direcaoVento
	               );
	    }
	}

