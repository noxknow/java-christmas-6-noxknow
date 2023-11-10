package christmas.handler;

public enum ErrorHandler {

    INCONVERTIBLE_TYPE("숫자로 변환될 수 없는 값입니다.");

    private final String errorMessage;

    ErrorHandler(String message) {
        this.errorMessage = "[ERROR] " + message;
    }

    public RuntimeException getException() {
        return new IllegalArgumentException(errorMessage);
    }
}
