package christmas.domain;

import java.util.Map;

import static christmas.handler.ConstantsHandler.*;

public class DateResult {

    private final int date;
    private final Map<String, Integer> orderedMenu;

    private DateResult(int date, Map<String, Integer> orderedMenu) {
        this.date = date;
        this.orderedMenu = orderedMenu;
    }

    public static DateResult of(int date, Map<String, Integer> orderedMenu) {
        return new DateResult(date, orderedMenu);
    }

    public int christmasDiscount() {
        if (date <= D_DAY) {
            return INIT_DISCOUNT + (date-INIT_DATE) * UNIT_OF_DISCOUNT;
        }

        return INIT_VALUE;
    }
}
