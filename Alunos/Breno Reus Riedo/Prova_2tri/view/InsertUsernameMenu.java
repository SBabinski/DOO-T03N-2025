package view;

import util.GlobalVarSingleton;
import util.JsonFileHandler;

public class InsertUsernameMenu extends BaseMenu{


    public static void display(){
        System.out.println("Esse é seu primeiro login");
        while (true){
            try {
                System.out.print("Coloque seu nome de usuario: ");
                String userName = input.readLine();

                if(userName.length() > 16){
                    System.out.println("Nome muito grande mano");
                    continue;
                }

                if(userName.isBlank()){
                    System.out.println("Nome não pode estar vazio");
                    continue;
                }

                GlobalVarSingleton.getInstance().userName = userName;
                JsonFileHandler.writeUsernameToFile(userName);
                break;
            }catch (Exception e){
                System.out.println("Alguma coisa deu muito errado!");
            }
        }
    }
}
