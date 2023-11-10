package christmas.domain.wrapper;

import static christmas.handler.ErrorHandler.INCONVERTIBLE_TYPE;

public class Date {

    private final int date;

    private Date(String dateString) {
        this.date = validateDateType(dateString);
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
}
