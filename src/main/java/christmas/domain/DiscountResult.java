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
        if (date <= D_DAY.getValue()) {
            return INIT_DISCOUNT.getValue() + (date-INIT_DATE.getValue()) * UNIT_OF_DISCOUNT.getValue();
        }

        return INIT_VALUE.getValue();
    }

    public int weeklyDiscount() {
        int discount = INIT_VALUE.getValue();
        int weeklyRemainder = date % DAYS_IN_A_WEEK.getValue();

        if (weeklyRemainder == THURSDAY_REMAINDER.getValue() || weeklyRemainder >= SUNDAY_REMAINDER.getValue() && weeklyRemainder <= WEDNESDAY_REMAINDER.getValue()) {
            discount += calculateDiscount("디저트");
        } else if (weeklyRemainder >= FRIDAY_REMAINDER.getValue() && weeklyRemainder <= SATURDAY_REMAINDER.getValue()) {
            discount += calculateDiscount("메인");
        }

        return discount;
    }

    public boolean isWeekend() {
        int weeklyRemainder = date % DAYS_IN_A_WEEK.getValue();

        return (weeklyRemainder >= FRIDAY_REMAINDER.getValue() && weeklyRemainder <= SATURDAY_REMAINDER.getValue());
    }

    private int calculateDiscount(String menuGroup) {
        int discount = INIT_VALUE.getValue();

        for (Map.Entry<String, Integer> entry : orderedMenu.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            MenuManager menuManager = MenuManager.getMenuManager(menu);

            if (menuManager.getGroup().equals(menuGroup)) {
                discount += PRESENT_YEAR.getValue() * quantity;
            }
        }

        return discount;
    }

    public int specialDiscount() {
        if (date % DAYS_IN_A_WEEK.getValue() == SUNDAY_REMAINDER.getValue() || date == D_DAY.getValue()) {
            return SPECIAL_DISCOUNT.getValue();
        }

        return INIT_VALUE.getValue();
    }

    public int eventDiscount(int beforeDiscountCost) {
        if (beforeDiscountCost >= MIN_AMOUNT_FOR_FREE_GIFT.getValue()) {
            return CHAMPAGNE_AMOUNT.getValue();
        }

        return INIT_VALUE.getValue();
    }

    public int totalDiscount(int beforeDiscountCost) {
        int christmasDiscount = christmasDiscount();
        int weeklyDiscount = weeklyDiscount();
        int specialDiscount = specialDiscount();
        int eventDiscount = eventDiscount(beforeDiscountCost);
        int totalDiscount = christmasDiscount + weeklyDiscount + specialDiscount + eventDiscount;

        return totalDiscount;
    }

    public String eventBadge(int totalDiscount) {
        if (totalDiscount >= SANTA_BADGE_AMOUNT.getValue()) {
            return "산타";
        } else if (totalDiscount >= TREE_BADGE_AMOUNT.getValue()) {
            return "트리";
        } else if (totalDiscount >= STAR_BADGE_AMOUNT.getValue()) {
            return "별";
        }

        return "없음";
    }
}
