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
    void createDateByIncovertibleType(String dateValue) {
        assertThatThrownBy(() -> EventDate.from(dateValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INCONVERTIBLE_TYPE.getException().getMessage());
    }
}
