package Fag;
import java.time.LocalDate;

public class Venda {
	String nome;
	int unid;
	float preco;
	float desc;
	float precoTotal;
	LocalDate data;
	
	public Venda(String nome, int unid, float preco, float desc, float precoTotal, LocalDate data) {
		this.data = data;
		this.nome = nome;
		this.unid = unid;
		this.preco = preco;
		this.desc = desc;
		this.precoTotal = precoTotal;
	}
}

