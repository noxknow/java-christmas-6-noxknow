package christmas.domain;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountResultTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

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

    private static Stream<Arguments> testWeeklyDiscount() {
        return Stream.of(
                Arguments.of(8, Map.of("티본스테이크", 1,
                        "제로콜라", 1), 2023),
                Arguments.of(9, Map.of("초코케이크", 1,
                        "바비큐립", 2), 4046),
                Arguments.of(3,Map.of("아이스크림", 4,
                        "해산물파스타", 1), 8092),
                Arguments.of(25, Map.of("초코케이크", 1,
                        "아이스크림", 2), 6069)
        );
    }

    private static Stream<Arguments> testSpecialDiscount() {
        return Stream.of(
                Arguments.of(25, Map.of("티본스테이크", 1,
                        "제로콜라", 1), 1000),
                Arguments.of(9, Map.of("초코케이크", 1,
                        "바비큐립", 2), 0),
                Arguments.of(3,Map.of("아이스크림", 4,
                        "해산물파스타", 1), 1000)
        );
    }

    private static Stream<Arguments> testEventDiscount() {
        return Stream.of(
                Arguments.of(25, Map.of("티본스테이크", 1,
                        "제로콜라", 1), 120000, 25000),
                Arguments.of(25, Map.of("티본스테이크", 1,
                        "제로콜라", 1), 119999, 0),
                Arguments.of(25, Map.of("티본스테이크", 1,
                        "제로콜라", 1), 140000, 25000)
        );
    }

    private static Stream<Arguments> testTotalDiscount() {
        return Stream.of(
                Arguments.of(25, Map.of("티본스테이크", 1,
                        "제로콜라", 1), 120000, 29400),
                Arguments.of(8, Map.of("초코케이크", 1,
                        "바비큐립", 2), 119999, 5746),
                Arguments.of(3, Map.of("아이스크림", 4,
                        "해산물파스타", 1), 140000, 35292)
        );
    }

    private static Stream<Arguments> testEventBadge() {
        return Stream.of(
                Arguments.of(25, Map.of("초코케이크", 1,
                        "제로콜라", 1), 1000, "없음"),
                Arguments.of(23, Map.of("티본스테이크", 1,
                        "제로콜라", 1), 6000, "별"),
                Arguments.of(8, Map.of("초코케이크", 1,
                        "바비큐립", 2), 11000, "트리"),
                Arguments.of(3, Map.of("아이스크림", 4,
                        "해산물파스타", 1), 25000, "산타")
        );
    }

    @DisplayName("크리스마스 디데이 할인이 정상적으로 적용된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testChristmasDiscount")
    void createChristmasDiscount(int date, Map<String, Integer> orderedMenu, int expectedDiscount) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.christmasDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("평일과 주말 할인이 정상적으로 적용된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testWeeklyDiscount")
    void createWeeklyDiscount(int date, Map<String, Integer> orderedMenu, int expectedDiscount) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.weeklyDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("특별 할인이 정상적으로 적용된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testSpecialDiscount")
    void createSpecialDiscount(int date, Map<String, Integer> orderedMenu, int expectedDiscount) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.specialDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("증정 이벤트 할인이 정상적으로 적용된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testEventDiscount")
    void createEventDiscount(int date, Map<String, Integer> orderedMenu, int beforeDiscountCost, int expectedDiscount) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.eventDiscount(beforeDiscountCost)).isEqualTo(expectedDiscount);
    }

    @DisplayName("총혜택 금액이 정상적으로 반환된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testTotalDiscount")
    void createTotalDiscount(int date, Map<String, Integer> orderedMenu, int beforeDiscountCost, int expectedDiscount) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.totalDiscount(beforeDiscountCost)).isEqualTo(expectedDiscount);
    }

    @DisplayName("이벤트 배지가 정상적으로 반환된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testEventBadge")
    void createEventBadge(int date, Map<String, Integer> orderedMenu, int totalDiscount, String expectedResult) {
        DiscountResult discountResult = DiscountResult.of(date, orderedMenu);

        assertThat(discountResult.eventBadge(totalDiscount)).isEqualTo(expectedResult);
    }

    @DisplayName("총주문 금액 10,000원 이하라면 이벤트가 적용되지 않는다.")
    @Test
    void applyEvent() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-1,제로콜라-1");
            Assertions.assertThat(output()).contains(
                    "<혜택 내역>" + LINE_SEPARATOR + "없음",
                    "<총혜택 금액>" + LINE_SEPARATOR + "0원",
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "8,000원",
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + "없음"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
