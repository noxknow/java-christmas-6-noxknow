package christmas.domain.wrapper;

import static christmas.handler.ConstantsHandler.MAX_DATE_NUMBER;
import static christmas.handler.ConstantsHandler.MIN_DATE_NUMBER;
import static christmas.handler.ErrorHandler.INCONVERTIBLE_TYPE;
import static christmas.handler.ErrorHandler.INVALID_DATE_RANGE;

public class Date {

    private final int date;

    private Date(String dateString) {
        this.date = validateDateType(dateString);
        validateDateRange(date);
    }

    public static Date from(String dateString) {
        return new Date(dateString);
    }

    private int validateDateType(String dateString) {
        try {
            return Integer.parseInt(dateString);
        } catch (NumberFormatException e) {
            throw INCONVERTIBLE_TYPE.getException();
        }
    }

    private void validateDateRange(int date) {
        if (date < MIN_DATE_NUMBER || date > MAX_DATE_NUMBER) {
            throw INVALID_DATE_RANGE.getException();
        }
    }
}
