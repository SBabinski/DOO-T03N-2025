package view;

import controller.ListsController;
import util.GlobalVarSingleton;
import util.ListasTipo;

public final class MainMenu extends BaseMenu {

    public static void display() {
        outer:
        while (true) {
            System.out.println("Bem vindo senhor " + GlobalVarSingleton.getInstance().userName);
            printMainMenu();
            try {
                int userChoice = readInt();

                switch (userChoice) {
                    case 1:
                        SearchShowsMenu.display();
                        break;
                    case 2:
                        SeeListMenu.display(ListsController.getThreeListsModel().getFavorites(), ListasTipo.FAVORITAS);
                        break;
                    case 3:
                        SeeListMenu.display(ListsController.getThreeListsModel().getAlreadyWatched(), ListasTipo.JA_ASSISTIDAS);
                        break;
                    case 4:
                        SeeListMenu.display(ListsController.getThreeListsModel().getToWatch(), ListasTipo.PRA_ASSISTIR);
                        break;
                    case 5:
                        break outer;
                }
            } catch (Exception e) {
                System.out.println("Digita certo ae");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println(
                """
                Sistema séries TV
                [1] Pesquisar Séries
                [2] Favoritas
                [3] Já assistidas
                [4] Deseja assistir
                [5] Sair -->\
                """);
    }
}
