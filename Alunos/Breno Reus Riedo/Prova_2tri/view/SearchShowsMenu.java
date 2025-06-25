package view;

import controller.ApiController;
import controller.ListsController;
import model.ShowDTO;

import java.util.List;

public final class SearchShowsMenu extends BaseMenu {

    private static List<ShowDTO> returnedShows;

    public static void display(){
        while(true){
            try {
                System.out.println("Digite o nome da série para pesquisar ('q' para sair): ");
                String userAnswer = input.readLine();

                if(userAnswer.equals("q")){
                    break;
                }

                System.out.println("Pesquisando...");
                returnedShows = ApiController.searchShowsByName(userAnswer);

                if(returnedShows == null){
                    System.out.println("Um erro ocorreu um erro ao pesquisar a série, voltando para o menu principal...");
                    break;
                }

                if(returnedShows.isEmpty()){
                    System.out.println("Nenhuma série com esse nome foi encontrada.");
                    continue;
                }
                int sizeReturnedShows = returnedShows.size();

                System.out.println(sizeReturnedShows + (sizeReturnedShows == 1 ? " Série encontrada" : " Séries encontradas"));

                showManagementMenu();
            }catch (Exception e){
                System.out.println("Alguma coisa deu errado");
            }
        }
    }

    private static void showManagementMenu(){
        while (true) {
            try {
                printShowsList();
                System.out.println("Selecione um para ver detalhes ('v' voltar para a pesquisa): ");
                String userChoice = input.readLine();

                if (userChoice.equals("v")) {
                    return;
                }

                ShowDTO selectedShow = returnedShows.get(Integer.parseInt(userChoice) - 1);

                printShowDetails(selectedShow);

                outer:
                while (true) {
                    System.out.println(
                            """
                                   Digite 'f' para adicionar aos favoritos,
                                   'a' se você já assistiu,
                                   'd' se deseja assistir,
                                   'v' para voltar\
                            """);

                    userChoice = input.readLine().charAt(0) + "";

                    switch (userChoice) {
                        case "f":
                            if (!ListsController.addToFavorites(selectedShow)) {
                                System.out.println("Série ja está adicionada na lista!");
                                continue;
                            }
                            break;
                        case "a":
                            if (!ListsController.addToAlreadyWatched(selectedShow)) {
                                System.out.println("Série ja está adicionada na lista!");
                                continue;
                            }
                            break;
                        case "d":
                            if (!ListsController.addToWatch(selectedShow)) {
                                System.out.println("Série ja está adicionada na lista!");
                                continue;
                            }
                            break;
                        case "v":
                            break outer;
                        default:
                            System.out.println("Digite certo!");
                            continue;
                    }

                    System.out.println("Adicionado com sucesso!");
                }

            } catch (Exception e) {
                System.out.println("Erro de seleção, escolha novamente!");
            }
        }

    }

    public static void printShowDetails(ShowDTO show){
        System.out.println("\n----------------DETALHES----------------");
        System.out.println("Nome: " + show.name());
        System.out.println("Linguagem: " + show.language());
        System.out.println("Gênero(s): " + show.genres());
        System.out.println("Avaliação: " + show.rating());
        System.out.println("Estado: " + show.status());
        System.out.println("Data de estreia: " + (show.premiered() == null ? "Desconhecida" : show.premiered()));
        System.out.println("Data de finalização: " + (show.ended() == null ? "Desconhecida" : show.ended()));
        System.out.println("Emissora: " + show.broadcaster());
        System.out.println("----------------------------------------\n");
    }

    private static void printShowsList(){
        int counter = 0;
        for(ShowDTO show : returnedShows){
            counter++;
            System.out.println("[" + counter + "]" + " " + show.name() + " [" + show.broadcaster() + "] ★" + show.rating());
        }
    }
}
