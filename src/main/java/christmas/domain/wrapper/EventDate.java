package christmas.domain.wrapper;

import static christmas.handler.ConstantsHandler.MAX_DATE_NUMBER;
import static christmas.handler.ConstantsHandler.MIN_DATE_NUMBER;
import static christmas.handler.ErrorHandler.INCONVERTIBLE_TYPE;
import static christmas.handler.ErrorHandler.INVALID_DATE_RANGE;

public class EventDate {

    private final int date;

    private EventDate(String dateValue) {
        this.date = validateDateType(dateValue);
        validateDateRange(date);
    }

    public static EventDate from(String dateValue) {
        return new EventDate(dateValue);
    }

    private int validateDateType(String dateValue) {
        try {
            return Integer.parseInt(dateValue);
        } catch (NumberFormatException e) {
            throw INCONVERTIBLE_TYPE.getException();
        }
    }

    private void validateDateRange(int date) {
        if (date < MIN_DATE_NUMBER || date > MAX_DATE_NUMBER) {
            throw INVALID_DATE_RANGE.getException();
        }
    }

    public int getEventDate() {
        return date;
    }
}
