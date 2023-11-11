package christmas.domain;

import static christmas.handler.ConstantsHandler.*;

public class DateResult {

    private final int date;

    private DateResult(int date) {
        this.date = date;
    }

    public static DateResult from(int date) {
        return new DateResult(date);
    }

    public int christmasDiscount() {
        return INIT_DISCOUNT + (date-INIT_DATE) * UNIT_OF_DISCOUNT;
    }
}
