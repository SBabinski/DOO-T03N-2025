package objetos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidos;
    private List<Serie> desejoAssistir;

    
    public Usuario() {
        this.favoritos = new ArrayList<>();
        this.assistidos = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
    }


    public Usuario(String nome) {
        this.nome = nome;
        this.favoritos = new ArrayList<>();
        this.assistidos = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
    }

   
    public String getNome() { return nome; }
    public List<Serie> getFavoritos() { return favoritos; }
    public List<Serie> getAssistidos() { return assistidos; }
    public List<Serie> getDesejoAssistir() { return desejoAssistir; }

    
    public void setNome(String nome) { this.nome = nome; }
    
    public void adicionarSerie(String nomeLista, Serie serieParaAdicionar) {
        List<Serie> listaAlvo = null;
 
        switch (nomeLista.toLowerCase()) {
            case "favoritos":
                listaAlvo = this.favoritos;
                break;
            case "desejoassistir":
                listaAlvo = this.desejoAssistir;
                break;
            case "assistidos":
                listaAlvo = this.assistidos;
                break;
            default:
                System.err.println("Erro: A lista '" + nomeLista + "' não existe.");
                return;
        }

      
        boolean jaContem = listaAlvo.stream().anyMatch(s -> s.id() == serieParaAdicionar.id());

        //if para separar, se ja tiver a serie na lista ou seja, jaContem vale true, então mostra a mensagem dizendo que ja esta na lista.
        if (jaContem) {
        	
                 	System.out.println("A série '" + serieParaAdicionar.getNome() + "' já está na sua lista de '" + nomeLista + "'.");
                 	
        // caso não esteja na lista, ou seja, jaContem é false, então ele adicona ListaAlvo (que possui como valor a lista escolhida pelo usuario)
        //e mostra mensagem dizendo que adicionou na lista.         	
        } else {
        	
         
        	listaAlvo.add(serieParaAdicionar);
        	
            System.out.println("'" + serieParaAdicionar.getNome() + "' foi adicionada com sucesso à lista '" + nomeLista + "'.");
        }
    }

    //metodo para remover serie da lista, passando por parametro o nome da lista que vai ser removida e o id da serie que vai ser removida.
    public boolean removerSerie(String nomeLista, int idSerie) {
     
    	//usamos uma variavel vazia para apontar de qual lista vai ser removida a serie
    	List<Serie> listaAlvo;
    	
    	//ve qual serie foi escolhida
        switch (nomeLista.toLowerCase()) {
        
        // a variavel vazia recebe a lista escolhida como valor
            case "favoritos":
                listaAlvo = this.favoritos;
                break;
                
            case "desejoassistir":
                listaAlvo = this.desejoAssistir;
                break;
                
            case "assistidos":
                listaAlvo = this.assistidos;
                break;
                
            default:
                System.err.println("Erro: A lista '" + nomeLista + "' não existe.");
                return false;
       
                
                
       
    }
        //se removeu algo retorna true, se nao, false
        //removeIf procura a serie com esse id e se acha remove ela.
        return listaAlvo.removeIf(serie -> serie.id() == idSerie);
    }
}

