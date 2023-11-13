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

    public int specialDiscount() {
        if (date % DAYS_IN_A_WEEK == SUNDAY_REMAINDER || date == D_DAY) {
            return SPECIAL_DISCOUNT;
        }

        return INIT_VALUE;
    }

    public int eventDiscount(int beforeDiscountCost) {
        if (beforeDiscountCost >= MIN_AMOUNT_FOR_FREE_GIFT) {
            return CHAMPAGNE_AMOUNT;
        }

        return INIT_VALUE;
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
        if (totalDiscount >= SANTA_BADGE_AMOUNT) {
            return "산타";
        } else if (totalDiscount >= TREE_BADGE_AMOUNT) {
            return "트리";
        } else if (totalDiscount >= STAR_BADGE_AMOUNT) {
            return "별";
        }

        return "없음";
    }
}
