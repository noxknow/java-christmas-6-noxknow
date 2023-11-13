package christmas.handler;

public enum ErrorHandler {

    INCONVERTIBLE_TYPE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DUPLICATE_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_QUANTITY_RANGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String errorMessage;

    ErrorHandler(String message) {
        this.errorMessage = "[ERROR] " + message;
    }

    public RuntimeException getException() {
        return new IllegalArgumentException(errorMessage);
    }
}
