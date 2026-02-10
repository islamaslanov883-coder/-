import menu.Menu;
import menu.GroceryMenu;

public class Main {
    public static void main(String[] args) {
        Menu storeMenu = new GroceryMenu();
        storeMenu.run();
    }
}
