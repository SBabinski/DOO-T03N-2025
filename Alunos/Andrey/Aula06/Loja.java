package Objeto;


public class Loja {
	private String nomeFantasia;
	private String razaoSocial;
	private Integer cnpj;
	private String cidade;
	private String bairro;
	private String rua;
	static Loja primeira = new Loja("Loja da Gabi", "Vendas Plantas", 819, "Cascavel", "Centro", "Presidente Kened");
	
	
	public Loja(String nomeFantasia, String razaoSocial, Integer cnpj, String cidade, String bairro, String rua) {
		super();
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}

	public static void visualizar() {
		System.out.println(primeira);
	}

	@Override
	public String toString() {
		return "Loja " + nomeFantasia + "\n"
				+ "razaoSocial " + razaoSocial + "\n"
				+ "cnpj " + cnpj + "\n"
				+ "cidade=" + cidade + "\n"
				+ "bairro=" + bairro + "\n"
				+ "rua=" + rua ;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public Integer getCnpj() {
		return cnpj;
	}
	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
}
