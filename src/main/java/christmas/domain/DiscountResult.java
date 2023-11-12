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

    public int weeklyDiscount() {
        int discount = INIT_VALUE;
        int weeklyRemainder = date % DAYS_IN_A_WEEK;

        if (weeklyRemainder == THURSDAY_REMAINDER || weeklyRemainder >= SUNDAY_REMAINDER && weeklyRemainder <= WEDNESDAY_REMAINDER) {
            discount += calculateDiscount("디저트");
        } else if (weeklyRemainder >= FRIDAY_REMAINDER && weeklyRemainder <= SATURDAY_REMAINDER) {
            discount += calculateDiscount("메인");
        }

        return discount;
    }

    private int calculateDiscount(String menuGroup) {
        int discount = INIT_VALUE;

        for (Map.Entry<String, Integer> entry : orderedMenu.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            MenuManager menuManager = MenuManager.getMenuManager(menu);

            if (menuManager.getGroup().equals(menuGroup)) {
                discount += PRESENT_YEAR * quantity;
            }
        }

        return discount;
    }
}
