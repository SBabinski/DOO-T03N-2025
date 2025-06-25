import util.GlobalVarSingleton;
import util.JsonFileHandler;
import view.InsertUsernameMenu;
import view.MainMenu;

public class Main {

    public static void main(String[] args) {
        JsonFileHandler.readDefaultFile();

        if(GlobalVarSingleton.getInstance().userName == null) {
            InsertUsernameMenu.display();
        }

        MainMenu.display();
    }
}
