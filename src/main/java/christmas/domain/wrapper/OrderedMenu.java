package christmas.domain.wrapper;

import java.util.*;

import static christmas.handler.ConstantsHandler.*;
import static christmas.handler.ErrorHandler.*;

public class OrderedMenu {

    private final Map<String, Integer> orderedMenu;

    private OrderedMenu(String menuValue) {
        this.orderedMenu = validateOrderFormat(menuValue);
        validateMenuDuplicate(orderedMenu);
        validateQuantityRange(orderedMenu);
    }

    public static OrderedMenu from(String menuValue) {
        return new OrderedMenu(menuValue);
    }

    private Map<String, Integer> validateOrderFormat(String menuValue) {
        try {
            return splitMenuValue(menuValue);
        } catch (NumberFormatException e) {
            throw INVALID_MENU_FORMAT.getException();
        }
    }

    private Map<String, Integer> splitMenuValue(String menuValue) {
        Map<String, Integer> orderedMenu = new HashMap<>();
        String[] items = menuValue.split(COMMA_DELIMITER);

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

    public Map<String, Integer> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenu);
    }
}
