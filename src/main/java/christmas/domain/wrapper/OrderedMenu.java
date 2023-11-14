package christmas.domain.wrapper;

import christmas.domain.MenuManager;

import java.util.*;

import static christmas.handler.ConstantsHandler.*;
import static christmas.handler.ErrorHandler.*;

public class OrderedMenu {

    private final Map<String, Integer> orderedMenu;

    private OrderedMenu(String menuValue) {
        this.orderedMenu = validateOrderFormat(menuValue);
        validateQuantityRange(orderedMenu);
        validateQuantitySize(orderedMenu);
        validateMenuType(orderedMenu);
        validateOnlyDrink(orderedMenu);
    }

    public static OrderedMenu from(String menuValue) {
        return new OrderedMenu(menuValue);
    }

    private Map<String, Integer> validateOrderFormat(String menuValue) {
        try {
            return splitMenuValue(menuValue);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw INVALID_MENU_FORMAT.getException();
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

            validateMenuDuplicate(orderedMenu, menu, quantity);
        }

        return orderedMenu;
    }

    private void validateMenuDuplicate(Map<String, Integer> orderedMenu, String menu, int quantity) {
        if (orderedMenu.containsKey(menu)) {
            throw DUPLICATE_MENU.getException();
        }

        orderedMenu.put(menu, quantity);
    }

    private void validateQuantityRange(Map<String, Integer> orderedMenu) {
        for (int quantity : orderedMenu.values()) {
            if (quantity < MIN_QUANTITY) {
                throw INVALID_QUANTITY_RANGE.getException();
            }
        }
    }

    private void validateQuantitySize(Map<String, Integer> orderedMenu) {
        int totalQuantity = INIT_VALUE;

        for (int quantity : orderedMenu.values()) {
            totalQuantity += quantity;
        }

        if (totalQuantity > MAX_QUANTITY) {
            throw INVALID_QUANTITY_SIZE.getException();
        }
    }

    private void validateMenuType(Map<String, Integer> orderedMenu) {
        for (String menu : orderedMenu.keySet()) {
            MenuManager menuManager = MenuManager.getMenuManager(menu);

            if (menuManager == null) {
                throw INVALID_MENU.getException();
            }
        }
    }

    private void validateOnlyDrink(Map<String, Integer> orderedMenu) {
        long groupCount = orderedMenu.keySet()
                .stream()
                .map(MenuManager::getMenuManager)
                .filter(manager -> manager.getGroup().equals("음료"))
                .count();

        if (orderedMenu.size() == groupCount) {
            throw NOT_ONLY_DRINK.getException();
        }
    }

    public Map<String, Integer> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenu);
    }
}
