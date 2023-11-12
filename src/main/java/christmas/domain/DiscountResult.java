package christmas.domain;

import java.util.Map;

import static christmas.handler.ConstantsHandler.*;

public class DiscountResult {

    private final int date;
    private final Map<String, Integer> orderedMenu;

    private DiscountResult(int date, Map<String, Integer> orderedMenu) {
        this.date = date;
        this.orderedMenu = orderedMenu;
    }

    public static DiscountResult of(int date, Map<String, Integer> orderedMenu) {
        return new DiscountResult(date, orderedMenu);
    }

    public int christmasDiscount() {
        if (date <= D_DAY) {
            return INIT_DISCOUNT + (date-INIT_DATE) * UNIT_OF_DISCOUNT;
        }

        return INIT_VALUE;
    }

    public int weekDayDiscount() {
        int weekDayDiscount = INIT_VALUE;

        if (date % DAYS_IN_A_WEEK == THURSDAY_REMAINDER || date % DAYS_IN_A_WEEK >= SUNDAY_REMAINDER && date % DAYS_IN_A_WEEK <= WEDNESDAY_REMAINDER) {
            int discount = checkDessert();

            return weekDayDiscount + discount;
        }

        return weekDayDiscount;
    }

    private int checkDessert() {
        int discount = INIT_VALUE;

        for (Map.Entry<String, Integer> entry : orderedMenu.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            MenuManager menuManager = MenuManager.getMenuManager(menu);

            if (menuManager.getGroup().equals("디저트")) {
                discount += PRESENT_YEAR * quantity;
            }
        }

        return discount;
    }
}
