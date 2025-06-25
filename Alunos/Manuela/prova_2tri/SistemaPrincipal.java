package avaliacao;

import objetos.*;



import java.util.List;

import java.util.Scanner;

import java.util.Comparator;

import java.util.InputMismatchException;

public class SistemaPrincipal {



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TVmazeAPI api = new TVmazeAPI();

        

      //pega os dados armazenados no arquivo do gerenciador de dados

        Usuario usuario = GerenciadorDados.carregarUsuario(null);

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {


            System.out.println("Bem vindo ao Sistema de Gerenciamento de Séries!");

            

            System.out.println("Qual o seu nome?");

            String nomeUsuario = scan.nextLine();

            

            usuario.setNome(nomeUsuario); 

        }

        

        

        System.out.println("\nOlá, " + usuario.getNome() + "! Segue abaixo Menu.");

        

        int opcao = -1;

        

        while (opcao != 0) {

            exibirMenu();

            

            try {

                System.out.print("Escolha uma opção: ");

                

                opcao = scan.nextInt();

                

                scan.nextLine(); 

                

                //le a opção e manda pra onde ele escolheu

                switch (opcao) {

                

                    case 1: 

                    	

                        buscarEAdicionarSerie(scan, api, usuario);

                        break;

                        

                    case 2: 

                    	

                        visualizarListas(scan, usuario);

                        break;

                        

                    case 3:

                    	

                        removerSerie(scan, usuario);

                        break;

                        

                    case 0:

                    	

                        GerenciadorDados.salvarUsuario(usuario); 

                        System.out.println("Obrigado por usar o catálogo. Até logo!");

                        break;

                        

                        

                        

                    default:

                    	

                        System.out.println("Opção inválida. Tente novamente!");

                        break;

                }

                



                if (opcao != 0) {

                	

                    System.out.println("\nPressione ENTER para continuar...");

                    scan.nextLine();

                }



                

            } catch (InputMismatchException e) {

            	

                System.err.println("Erro: Por favor, digite um número válido para a opção do menu.");

                scan.nextLine(); 

                opcao = -1; 

            }

        }

        

        scan.close();

    }

    

  

    public static void exibirMenu() {

    	

        System.out.println("\nMenu - escolha a opção que deseja:");

        System.out.println("1 - Buscar e adicionar nova série");

        System.out.println("2 - Visualizar minhas listas");

        System.out.println("3 - Remover série de uma lista");

        System.out.println("0 - Sair e Salvar");

       

    }

    

    

    public static void buscarEAdicionarSerie(Scanner scan, TVmazeAPI api, Usuario usuario) {

    	

        System.out.println("Digite o nome da série que você deseja buscar:");

        String nomeSerie = scan.nextLine();

        

        System.out.println("Buscando \"" + nomeSerie + "\" na API...");

       

        List<Serie> seriesEncontradas = api.buscarSeries(nomeSerie);

        

        if (seriesEncontradas == null || seriesEncontradas.isEmpty()) {

        	

            System.out.println("Desculpe, não foi possível encontrar nenhuma série com este nome.");

            return;

        }

        

        System.out.println("\n--- Resultados da Busca ---");

       

        for (int i = 0; i < seriesEncontradas.size(); i++) {

            

        	System.out.printf("%d. %s (%s)\n", (i + 1), seriesEncontradas.get(i).getNome(), seriesEncontradas.get(i).getEstreia());

        }

        

        System.out.print("\nDigite o número da série para ver detalhes e adicionar (ou 0 para cancelar): ");

      

        int escolhaSerie = scan.nextInt();

      

        scan.nextLine();

        

        if (escolhaSerie <= 0 || escolhaSerie > seriesEncontradas.size()) {

           

        	System.out.println("Seleção cancelada ou inválida.");

            return;

        }

        

        Serie serieEscolhida = seriesEncontradas.get(escolhaSerie - 1);

        

        System.out.println("\nSérie selecionada:");

      

        serieEscolhida.display();

        

        System.out.println("\nEm qual lista você deseja adicionar esta série?");

        System.out.println("1 - Favoritos");

        System.out.println("2 - Desejo Assistir");

        System.out.println("3 - Já Assistidos");

        System.out.print("Escolha uma lista (ou qualquer outro número para cancelar): ");

       

        int escolhaLista = scan.nextInt();

        scan.nextLine();

        

        String nomeLista = "";

      

        switch (escolhaLista) {

           

        case 1: nomeLista = "favoritos"; break;

            

        case 2: nomeLista = "desejoAssistir"; break;

          

        case 3: nomeLista = "assistidos"; break;

           

        default:

                System.out.println("Operação cancelada.");

                return; 

        }

        

        usuario.adicionarSerie(nomeLista, serieEscolhida);

    }

    

    public static void visualizarListas(Scanner scan, Usuario usuario) {

     

    	System.out.println("\nQual lista você quer ver?");

        System.out.println("1 - Favoritos");

        System.out.println("2 - Desejo Assistir");

        System.out.println("3 - Já Assistidos");

        System.out.print("Escolha uma lista: ");

        

        int escolhaLista = scan.nextInt();

        scan.nextLine();



        List<Serie> listaSelecionada;

       

        switch (escolhaLista) {

          

        case 1: listaSelecionada = usuario.getFavoritos(); break;

           

        case 2: listaSelecionada = usuario.getDesejoAssistir(); break;

           

        case 3: listaSelecionada = usuario.getAssistidos(); break;  

        

        default: System.out.println("Opção inválida."); return;

        

        }



        if (listaSelecionada.isEmpty()) {

           

        	System.out.println("Esta lista está vazia.");

            return;

        }



        System.out.println("\nComo deseja ordenar?");

        System.out.println("1 - Ordem Alfabética (padrão)");

        System.out.println("2 - Nota (maior para menor)");

        System.out.println("3 - Estado (Status)");

        System.out.println("4 - Data de Estreia (mais recente primeiro)");

        System.out.print("Escolha a ordenação: ");

     

        int escolhaOrdem = scan.nextInt();

        scan.nextLine();



        switch (escolhaOrdem) {

           

        //sort, ordena a lista de acordo com algum criterio. comparator.comparing (diz que é baseado no nome que deve ordenar), mesma coisa os outros, so que com nota, status etc...

        case 1: listaSelecionada.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER)); break;

          

        case 2: listaSelecionada.sort(Comparator.comparing(Serie::getNota).reversed()); break;

          

        case 3: listaSelecionada.sort(Comparator.comparing(Serie::getEstado)); break;

          

        case 4: listaSelecionada.sort(Comparator.comparing(Serie::getEstreia).reversed()); break;

          

            default: listaSelecionada.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER)); break;

        }

//mostra a lista ja ordenada

        System.out.println("\n--- LISTA ORDENADA ---");

        

        for (Serie serie : listaSelecionada) {

            serie.display();

        }

    }

    

    public static void removerSerie(Scanner scan, Usuario usuario) {

      

    	System.out.println("\nDe qual lista você deseja remover uma série?");

        System.out.println("1 - Favoritos");

        System.out.println("2 - Desejo Assistir");

        System.out.println("3 - Já Assistidos");

        System.out.print("Escolha uma lista: ");

       

        int escolhaLista = scan.nextInt();

        scan.nextLine();



        List<Serie> lista;

        String nomeLista;



        switch (escolhaLista) {

          

        case 1: lista = usuario.getFavoritos(); nomeLista = "favoritos"; break;

           

        case 2: lista = usuario.getDesejoAssistir(); nomeLista = "desejoAssistir"; break;

                    

        case 3: lista = usuario.getAssistidos(); nomeLista = "assistidos"; break;

           

        default: System.out.println("Opção inválida."); return;

        }



        //verifica se nao esta vazia

        if (lista.isEmpty()) {

          

        	System.out.println("Esta lista está vazia. Nada para remover.");

            return;

        }



        // mostra a lista das series que ele escolheu

        System.out.println("\nSéries em '" + nomeLista + "':");

       

        for (int i = 0; i < lista.size(); i++) {

            System.out.printf("%d - %s (ID: %d)\n", (i + 1), lista.get(i).getNome(), lista.get(i).id());

        }



        System.out.print("Digite o número da série para remover (ou 0 para cancelar): ");

     

        int escolhaSerie = scan.nextInt();

        scan.nextLine();



        // verifica se o numero é valido, se for, salva qual serie é pra remover e manda pra classe usuario remover

        if (escolhaSerie > 0 && escolhaSerie <= lista.size()) {

           

        	Serie serieParaRemover = lista.get(escolhaSerie - 1);

            if (usuario.removerSerie(nomeLista, serieParaRemover.id())) {

             

            	System.out.println("'" + serieParaRemover.getNome() + "' foi removida com sucesso!");

            

            } else {

            	

                System.out.println("Ocorreu um erro ao remover a série.");

            }

       

        } else {

        	

            System.out.println("Seleção cancelada ou inválida.");

        }

    }

}

