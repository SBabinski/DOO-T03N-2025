package util;

import controller.ListsController;
import model.JsonFileModel;
import model.ThreeListsModel;

import java.io.File;

public final class JsonFileHandler {

    private static final File appJson = new File("sistemaSeriesTV.json");

    public static void readDefaultFile(){

        if (!appJson.exists() || !appJson.isFile()) {
            System.out.println("Arquivo json não existe, criando em " + appJson.getAbsolutePath());
            try {
                if (appJson.createNewFile()) {
                    GlobalVarSingleton.getInstance().globalJsonWrapper.writeValue(appJson, new JsonFileModel(GlobalVarSingleton.getInstance().userName, new ThreeListsModel()));
                    System.out.println("Arquivo criado com sucesso!");
                } else {
                    System.out.println("Erro, arquivo ja existe (parece ser uma pasta com o mesmo nome), saindo...");
                    System.exit(1);
                }
            } catch (Exception e) {
                System.out.println("Erro ao criar arquivo json, saindo...\n" + e.getMessage());
                System.exit(1);
            }
        }

        JsonFileModel jsonFileModel;
        try {
            jsonFileModel = GlobalVarSingleton.getInstance().globalJsonWrapper.readValue(appJson, JsonFileModel.class);
            GlobalVarSingleton.getInstance().userName = jsonFileModel.userName();
            ListsController.setThreeListsModel(jsonFileModel.lists());
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo json...\n" + e.getMessage());
            System.exit(1);
        }

    }

    public static void writeListsToFile(ThreeListsModel threeListsModel){
        try {
            GlobalVarSingleton.getInstance().globalJsonWrapper.writeValue(appJson, new JsonFileModel(GlobalVarSingleton.getInstance().userName, threeListsModel));
        }catch (Exception e){
            System.out.println("Suas alterações não foram salvas!, erro ao escrever no arquivo json\n" + e.getMessage());
        }
    }

    public static void writeUsernameToFile(String username){
        try {
            GlobalVarSingleton.getInstance().globalJsonWrapper.writeValue(appJson, new JsonFileModel(username, ListsController.getThreeListsModel()));
        }catch (Exception e){
            System.out.println("Suas alterações não foram salvas!, erro ao escrever no arquivo json\n" + e.getMessage());
        }
    }
}
