package christmas.domain;

import java.util.Map;

import static christmas.handler.ConstantsHandler.INIT_VALUE;

public class MenuResult {

    private final Map<String, Integer> orderedMenu;

    private MenuResult(Map<String, Integer> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public static MenuResult from(Map<String, Integer> orderedMenu) {
        return new MenuResult(orderedMenu);
    }

    public int calculateCostBeforeDiscount() {
        int costBeforeDiscount = INIT_VALUE.getValue();

        for (Map.Entry<String, Integer> entry : orderedMenu.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            MenuManager menuManager = MenuManager.getMenuManager(menu);

            costBeforeDiscount += menuManager.getCost() * quantity;
        }

        return costBeforeDiscount;
    }
}
