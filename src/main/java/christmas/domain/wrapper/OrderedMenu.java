package christmas.domain.wrapper;

import java.util.HashMap;
import java.util.Map;

import static christmas.handler.ConstantsHandler.COMMA_DELIMITER;
import static christmas.handler.ConstantsHandler.DASH_DELIMITER;
import static christmas.handler.ErrorHandler.INVALID_MENU_FORMAT;

public class OrderedMenu {

    private final Map<String, Integer> orderedMenu;

    private OrderedMenu(String menuString) {
        this.orderedMenu = validateOrderFormat(menuString);
    }

    public static OrderedMenu from(String menuString) {
        return new OrderedMenu(menuString);
    }

    private Map<String, Integer> validateOrderFormat(String menuString) {
        try {
            return splitMenuString(menuString);
        } catch (NumberFormatException e) {
            throw INVALID_MENU_FORMAT.getException();
        }
    }

    private Map<String, Integer> splitMenuString(String menuString) {
        Map<String, Integer> orderedMenu = new HashMap<>();
        String[] items = menuString.split(COMMA_DELIMITER);

        for (String item : items) {
            String[] menuStructure = item.split(DASH_DELIMITER);
            String menu = menuStructure[0];
            int quantity = Integer.parseInt(menuStructure[1]);

            orderedMenu.put(menu, quantity);
        }

        return orderedMenu;
    }
}
