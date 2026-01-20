import menu.Menu;
import menu.StoreMenu;

public class Main {
    public static void main(String[] args) {
        Menu storeMenu = new StoreMenu(); // Полиморфизм интерфейса
        storeMenu.run(); // Запуск логики
    }
}

