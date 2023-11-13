package christmas.domain.wrapper;

import christmas.handler.ErrorHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EventDateTest {

    @DisplayName("숫자로 변환될 수 없는 값이 들어온다면 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @ValueSource(strings = {"일", " ", "@"})
    void createDateByInconvertibleType(String dateValue) {
        assertThatThrownBy(() -> EventDate.from(dateValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INCONVERTIBLE_TYPE.getException().getMessage());
    }

    @DisplayName("날짜의 범위가 1 ~ 31 사이의 숫자가 아니라면 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @ValueSource(strings = {"40", "0", "-50"})
    void createDateByInvalidRange(String dateValue) {
        assertThatThrownBy(() -> EventDate.from(dateValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_DATE_RANGE.getException().getMessage());
    }
}
