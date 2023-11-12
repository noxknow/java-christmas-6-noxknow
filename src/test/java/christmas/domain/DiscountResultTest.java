package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountResultTest {

    private static Stream<Arguments> testChristmasDiscount() {
        return Stream.of(
                Arguments.of(1, Map.of("타파스", 1,
                        "제로콜라", 1), 1000),
                Arguments.of(5, Map.of("타파스", 1,
                        "제로콜라", 1), 1400),
                Arguments.of(25,Map.of("타파스", 1,
                        "제로콜라", 1), 3400),
                Arguments.of(30, Map.of("타파스", 1,
                        "제로콜라", 1), 0)
        );
    }

    private static Stream<Arguments> testWeekDayDiscount() {
        return Stream.of(
                Arguments.of(5, Map.of("타파스", 1,
                        "제로콜라", 1), 0),
                Arguments.of(5, Map.of("초코케이크", 1,
                        "바비큐립", 2), 2023),
                Arguments.of(5,Map.of("아이스크림", 2,
                        "시저샐러드", 1), 4046),
                Arguments.of(5, Map.of("초코케이크", 1,
                        "아이스크림", 2), 6069)
        );
    }

    @DisplayName("크리스마스 디데이 할인이 정상적으로 적용된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testChristmasDiscount")
    void createChristmasDiscount(int date, Map<String, Integer> orderedMenu, int expectedDiscount) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.christmasDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("평일 할인이 정상적으로 적용된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testWeekDayDiscount")
    void createWeekDayDiscount(int date, Map<String, Integer> orderedMenu, int expectedDiscount) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.weeklyDiscount()).isEqualTo(expectedDiscount);
    }
}
