package objetos;

import java.util.List;



public class Serie {

	

	private int id;

	private String name;

	private String language;

	private List <String> genres;

	private Rating rating;

    private String status;

    private String premiered;

    private String ended;

    private Network network;

    // média de avaliação

    public static class Rating{

    	

    	private Double average;

    }

     

    //qual emissora da série.

    public static class Network {

    	

    	private String name;

    }


    public String getNome() {return this.name; }

    public int id( ) { return id;}

    public String getIdioma( ) { return language;}

    public List<String> getGeneros() {return genres;}

    public String getEstado() {return status;}

    public String getEstreia( ) { return premiered;}

 

    public Double getNota() {

    	if( rating != null && rating.average!= null ) {

    		

    		return rating.average;

    		

    	}

    	

    	return 0.0;

    	

    }

  //data de fim da série.

    public String getFim() {

    	

    	if(ended != null) {

    		

    		return ended;

    	}

    	

    	return "Presente";

    }

    

 

    public String getEmissora() {

    	

    	if( network != null && network.name != null) {

    		

    		return network.name;

    		

    	}

    	

    	return "Não há";

    }

    public void display() {

    	

    	 System.out.println("------------------------------------------");

         System.out.println("Nome: " + getNome());

         System.out.println("Idioma: " + getIdioma());

         System.out.println("Gêneros: " + String.join(", ", getGeneros()));

         System.out.println("Nota: " + getNota() + "/10");

         System.out.println("Estado: " + getEstado());

         System.out.println("Estreia: " + getEstreia());

         System.out.println("Fim: " + getFim());

         System.out.println("Emissora: " + getEmissora());

         System.out.println("------------------------------------------");	

  	

    }

   

    }