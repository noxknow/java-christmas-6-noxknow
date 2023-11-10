package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MenuResultTest {

    private static Stream<Arguments> testMenu() {
        return Stream.of(
                Arguments.of(Map.of("타파스", 1,
                        "제로콜라", 1), 8500),
                Arguments.of(Map.of("티본스테이크", 1,
                        "바비큐립", 1,
                        "초코케이크", 2,
                        "제로콜라", 1), 142000)
        );
    }

    @DisplayName("할인 전 총주문 금액이 정상적으로 반환된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testMenu")
    void createTotalCost(Map<String, Integer> testMenu, int expectedCost) {
        MenuResult menuResult = MenuResult.from(testMenu);

        assertThat(menuResult.totalCost()).isEqualTo(expectedCost);
    }
}
