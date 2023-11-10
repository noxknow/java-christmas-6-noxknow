package christmas.domain.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.handler.ConstantsHandler.*;
import static christmas.handler.ErrorHandler.*;

public class OrderedMenu {

    private final Map<String, Integer> orderedMenu;

    private OrderedMenu(String menuString) {
        this.orderedMenu = validateOrderFormat(menuString);
        validateMenuDuplicate(orderedMenu);
        validateQuantityRange(orderedMenu);
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
            String menu = menuStructure[FIRST_ELEMENT];
            int quantity = Integer.parseInt(menuStructure[SECOND_ELEMENT]);

            orderedMenu.put(menu, quantity);
        }

        return orderedMenu;
    }

    private void validateMenuDuplicate(Map<String, Integer> orderedMenu) {
        List<String> uniqueMenus = new ArrayList<>();

        for (String menu : orderedMenu.keySet()) {
            if (uniqueMenus.contains(menu)) {
                throw DUPLICATE_MENU.getException();
            }

            uniqueMenus.add(menu);
        }
    }

    private void validateQuantityRange(Map<String, Integer> orderedMenu) {
        for (int quantity : orderedMenu.values()) {
            if (quantity < MIN_QUANTITY) {
                throw INVALID_QUANTITY_RANGE.getException();
            }
        }
    }
}
