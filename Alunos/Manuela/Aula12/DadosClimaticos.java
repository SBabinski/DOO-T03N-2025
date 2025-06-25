package br.com.meuprojeto.tempo;

	public class DadosClimaticos {
		
		
		  String localizacao;
		    double temperaturaAtual;
		    double temperaturaMaxima;
		    double temperaturaMinima;
		    double umidade;
		    String condicoes;
		    double precipitacao;
		    double velocidadeVento;
		    double direcaoVento;
		
		public DadosClimaticos (
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
			
		}

		public String getLocalizacao() {
			return localizacao;
		}

		public void setLocalizacao(String localizacao) {
			this.localizacao = localizacao;
		}

		public double getTemperaturaAtual() {
			return temperaturaAtual;
		}

		public void setTemperaturaAtual(double temperaturaAtual) {
			this.temperaturaAtual = temperaturaAtual;
		}

		public double getTemperaturaMaxima() {
			return temperaturaMaxima;
		}

		public void setTemperaturaMaxima(double temperaturaMaxima) {
			this.temperaturaMaxima = temperaturaMaxima;
		}

		public double getTemperaturaMinima() {
			return temperaturaMinima;
		}

		public void setTemperaturaMinima(double temperaturaMinima) {
			this.temperaturaMinima = temperaturaMinima;
		}

		public double getUmidade() {
			return umidade;
		}

		public void setUmidade(double umidade) {
			this.umidade = umidade;
		}

		public String getCondicoes() {
			return condicoes;
		}

		public void setCondicoes(String condicoes) {
			this.condicoes = condicoes;
		}

		public double getPrecipitacao() {
			return precipitacao;
		}

		public void setPrecipitacao(double precipitacao) {
			this.precipitacao = precipitacao;
		}

		public double getVelocidadeVento() {
			return velocidadeVento;
		}

		public void setVelocidadeVento(double velocidadeVento) {
			this.velocidadeVento = velocidadeVento;
		}

		public double getDirecaoVento() {
			return direcaoVento;
		}

		public void setDirecaoVento(double direcaoVento) {
			this.direcaoVento = direcaoVento;
		}

		@Override
		public String toString() {
			return "DadosClimaticos [localizacao=" + localizacao + ", temperaturaAtual=" + temperaturaAtual
					+ ", temperaturaMaxima=" + temperaturaMaxima + ", temperaturaMinima=" + temperaturaMinima
					+ ", umidade=" + umidade + ", condicoes=" + condicoes + ", precipitacao=" + precipitacao
					+ ", velocidadeVento=" + velocidadeVento + ", direcaoVento=" + direcaoVento + ", getLocalizacao()="
					+ getLocalizacao() + ", getTemperaturaAtual()=" + getTemperaturaAtual()
					+ ", getTemperaturaMaxima()=" + getTemperaturaMaxima() + ", getTemperaturaMinima()="
					+ getTemperaturaMinima() + ", getUmidade()=" + getUmidade() + ", getCondicoes()=" + getCondicoes()
					+ ", getPrecipitacao()=" + getPrecipitacao() + ", getVelocidadeVento()=" + getVelocidadeVento()
					+ ", getDirecaoVento()=" + getDirecaoVento() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}

		
		
		
		  
}
