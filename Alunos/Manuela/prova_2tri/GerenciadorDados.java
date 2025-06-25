package objetos;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import java.io.*;



public class GerenciadorDados {

	

	//variavel de texto com o nome onde os dados do usuario vão ser salvos

	private static final String NOME_ARQUIVO = "dados_usuario.json";

	//coverte objetos java em json e vice verso e formata o json, com quebra de linha

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    // O primeiro método serve para criar um arquivo ou substituir, já o segundo é onde o sistema tenta abrir o arquivo com os dados que foram salvos

    // Exemplo se fecharmos o sistema os arquivos ficam salvos ali.

    public static void salvarUsuario(Usuario usuario) {

    	

    	  try (Writer writer = new FileWriter(NOME_ARQUIVO)) {

    		  

              gson.toJson(usuario, writer);//"escreve" no arquivo

              

              System.out.println("Dados salvos com sucesso!");

              

          } catch (IOException e) {

        	  

              System.err.println("Erro ao salvar os dados: " + e.getMessage());//exibe o erro

          }

      }

    

    public static Usuario carregarUsuario(String nomePadrao) {

    	

    	

        try (Reader reader = new FileReader(NOME_ARQUIVO)) {

        	

            Usuario usuario = gson.fromJson(reader, Usuario.class);//le e tranforma json em objetos.java

            

            System.out.println("Dados do usuário " + usuario.getNome() + " carregados.");

            

            return usuario;

            

        } catch (FileNotFoundException e) {

        	

            System.out.println("Arquivo de dados não encontrado. Criando novo usuário.");

            

            return new Usuario(nomePadrao);

            

        } catch (IOException e) {

        	

            System.err.println("Erro ao carregar os dados: " + e.getMessage());

           

            return new Usuario(nomePadrao);

        }

    }

}

