package Objetos;

import java.time.LocalDate;
import org.json.JSONArray;

public class Serie {
	private Integer id;
	private static Integer contId = 1;
	private String nome;
	private String lingua;
	private JSONArray genero;
	private Double nota;
	private LocalDate dataInicio;
	private String dataFim;
	private String canal;
	private String estado;
	
	
	public Serie(String nome, String lingua, JSONArray genero, Double nota, LocalDate dataInicio, String dataFim, String canal,
			String estado) {
		super();
		this.id = contId;
		this.nome = nome;
		this.lingua = lingua;
		this.genero = genero;
		this.nota = nota;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.canal = canal;
		this.estado = estado;
		
		contId = contId + 1;
	}
	
	public Serie(Integer id2, String nome2, String lingua2, JSONArray genero2, Double nota2, LocalDate dataInicio2,
			String dataFim2, String canal2, String estado2) {
		this.id = id2;
		this.nome = nome2;
		this.lingua = lingua2;
		this.genero = genero2;
		this.nota = nota2;
		this.dataInicio = dataInicio2;
		this.dataFim = dataFim2;
		this.canal = canal2;
		this.estado = estado2;
	}

	public Integer getId() {
		return id;
	}
	public void setNome(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public JSONArray getGenero() {
		return genero;
	}
	public void setGenero(JSONArray genero) {
		this.genero = genero;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "nome da serie = " + nome + "\n" 
				+ "lingua = " + lingua + "\n" 
				+ "genero = " + genero + "\n" 
				+ "nota = " + nota + "\n" 
				+ "dataInicio = " + dataInicio +  "\n" 	
				+ "dataFim = " + dataFim + "\n" 
				+ "canal = " + canal +  "\n" 
				+ "estado = " + estado + "\n";
	}
	
}
