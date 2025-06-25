package view;

import controller.ListsController;
import model.ShowDTO;
import util.ListasTipo;

import java.util.Comparator;
import java.util.List;

public class SeeListMenu extends BaseMenu{

    public static void display(List<ShowDTO> list, ListasTipo tipo){
        outer:
        while(true){
            try {
                if(list.isEmpty()){
                    System.out.println("Lista vazia!");
                    return;
                }

                System.out.println(
                        "\nSelecione um para remover, 'v' para voltar,\n" +
                        " 'a' para mostrar em ordem alfabetica,\n" +
                        " 'n' para mostrar em ordem por nota decrescente,\n" +
                        " 'e' para mostrar em ordem por status da série,\n" +
                        " 'd' para mostrar em ordem por data de estreia\n ");
                int counter = 0;
                for(ShowDTO show : list){
                    counter++;
                    System.out.println("[" + counter + "]" + " " + show.name() + " [" + show.broadcaster() + "] ★" + show.rating());
                }
                String userChoice = input.readLine();

                switch (userChoice) {
                    case "v":
                        break outer;
                    case "a":
                        showByAlphabeticOrder(list);
                        continue outer;
                    case "n":
                        showByRatingOrder(list);
                        continue outer;
                    case "e":
                        showByStatus(list);
                        continue outer;
                    case "d":
                        showByDateOrder(list);
                        continue outer;
                }

                ShowDTO selectedShow = list.get(Integer.parseInt(userChoice) - 1);

                SearchShowsMenu.printShowDetails(selectedShow);

                System.out.println("Você quer remover da lista? s/n");
                userChoice = input.readLine();
                if(userChoice.equals("s")){
                    switch (tipo){
                        case FAVORITAS -> ListsController.removeFromFavorites(selectedShow);
                        case JA_ASSISTIDAS -> ListsController.removeFromAlreadyWatched(selectedShow);
                        case PRA_ASSISTIR -> ListsController.removeFromWatch(selectedShow);
                    }
                    System.out.println("Removido com sucesso!");
                }

            }catch (Exception e){
                System.out.println("Digita certo ae");
            }
        }
    }

    private static void showByAlphabeticOrder(List<ShowDTO> list){
        System.out.println("Por nome: ");
        List<ShowDTO> byName = list.stream()
                .sorted(Comparator.comparing(ShowDTO::name))
                .toList();

        byName.forEach(
                show -> System.out.println(show.name() + " [" + show.broadcaster() + "] ★" + show.rating()  + " <" + show.premiered() + "> Status [" + show.status() + "]")
        );
    }

    private static void showByRatingOrder(List<ShowDTO> list){
        System.out.println("Por rating: ");
        List<ShowDTO> byRating = list.stream()
                .sorted(Comparator.comparingDouble(ShowDTO::rating)
                        .reversed())
                .toList();

        byRating.forEach(
                show -> System.out.println(show.name() + " [" + show.broadcaster() + "] ★" + show.rating()  + " <" + show.premiered() + "> Status [" + show.status() + "]")
        );
    }

    private static void showByStatus(List<ShowDTO> list){
        System.out.println("Por status: ");
        List<ShowDTO> byStatus = list.stream()
                .sorted(Comparator.comparing(ShowDTO::status))
                .toList();

        byStatus.forEach(
                show -> System.out.println(show.name() + " [" + show.broadcaster() + "] ★" + show.rating()  + " <" + show.premiered() + "> Status [" + show.status() + "]")
        );
    }

    private static void showByDateOrder(List<ShowDTO> list){
        System.out.println("Por data: ");
        List<ShowDTO> byDate = list.stream()
                .sorted(Comparator.comparing(ShowDTO::premiered)
                        .reversed())
                .toList();

        byDate.forEach(
                show -> System.out.println(show.name() + " [" + show.broadcaster() + "] ★" + show.rating()  + " <" + show.premiered() + "> Status [" + show.status() + "]")
        );
    }
}
