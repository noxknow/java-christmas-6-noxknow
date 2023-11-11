package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DateResultTest {

    private static Stream<Arguments> testChristmasDiscount() {
        return Stream.of(
                Arguments.of(1, 1000),
                Arguments.of(5, 1400),
                Arguments.of(25, 3400),
                Arguments.of(30, 0)
        );
    }

    @DisplayName("크리스마스 디데이 할인이 정상적으로 적용된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testChristmasDiscount")
    void createChristmasDiscount(int date, int expectedDiscount) {
        DateResult dateResult = DateResult.from(date);

        assertThat(dateResult.christmasDiscount()).isEqualTo(expectedDiscount);
    }
}
