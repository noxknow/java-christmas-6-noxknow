package christmas.handler;

public enum ConstantsHandler {

    MIN_DATE_NUMBER(1, ""),
    MAX_DATE_NUMBER(31, ""),

    COMMA_DELIMITER(-1, ","),
    DASH_DELIMITER(-1, "-"),
    FIRST_ELEMENT(0, ""),
    SECOND_ELEMENT(1, ""),
    MIN_QUANTITY(1, ""),
    MAX_QUANTITY(20, ""),
    INIT_VALUE(0, ""),

    INIT_DISCOUNT(1000, ""),
    SPECIAL_DISCOUNT(1000, ""),
    UNIT_OF_DISCOUNT(100, ""),
    INIT_DATE(1, ""),
    D_DAY(25, ""),
    DAYS_IN_A_WEEK(7, ""),
    SUNDAY_REMAINDER(3, ""),
    THURSDAY_REMAINDER(0, ""),
    WEDNESDAY_REMAINDER(6, ""),
    FRIDAY_REMAINDER(1, ""),
    SATURDAY_REMAINDER(2, ""),
    PRESENT_YEAR(2023, ""),
    MIN_AMOUNT_FOR_FREE_GIFT(120000, ""),
    CHAMPAGNE_AMOUNT(25000, ""),
    STAR_BADGE_AMOUNT(5000, ""),
    TREE_BADGE_AMOUNT(10000, ""),
    SANTA_BADGE_AMOUNT(20000, ""),
    MIN_APPLY_EVENT_AMOUNT(10000, "");

    private final int value;
    private final String word;

    ConstantsHandler(int value, String word) {
        this.value = value;
        this.word = word;
    }

    public int getValue() {
        return value;
    }

    public String getWord() {
        return word;
    }
}
