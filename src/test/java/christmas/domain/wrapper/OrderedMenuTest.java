package christmas.domain.wrapper;

import christmas.handler.ErrorHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class OrderedMenuTest {

    private static Stream<Arguments> testMenu() {
        return Stream.of(
                Arguments.of(OrderedMenu.from("해산물파스타-1,레드와인-2"), Map.of("해산물파스타", 1,
                        "레드와인", 2)),
                Arguments.of(OrderedMenu.from("타파스-3,제로콜라-2"), Map.of("타파스", 3,
                        "제로콜라", 2))
        );
    }

    @DisplayName("메뉴 형식이 다른 경우 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @ValueSource(strings = {"해산물파스타", "해산물파스타-삼", "해산물파스타,3", "레드와인-@"})
    void createMenuByInvalidMenuFormat(String menuValue) {
        assertThatThrownBy(() -> OrderedMenu.from(menuValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_MENU_FORMAT.getException().getMessage());
    }

    @DisplayName("중복된 메뉴가 있는 경우 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @ValueSource(strings = {"해산물파스타-1,해산물파스타-2", "레드와인-1,레드와인-41"})
    void createMenuByDuplicateMenu(String menuValue) {
        assertThatThrownBy(() -> OrderedMenu.from(menuValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.DUPLICATE_MENU.getException().getMessage());
    }

    @DisplayName("메뉴 개수가 1개 보다 적다면 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @ValueSource(strings = {"해산물파스타-0,레드와인-1", "해산물파스타--50,레드와인-41"})
    void createMenuByInvalidQuantityRange(String menuValue) {
        assertThatThrownBy(() -> OrderedMenu.from(menuValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_QUANTITY_RANGE.getException().getMessage());
    }

    @DisplayName("메뉴판에 없는 메뉴라면 예외가 발생한다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @ValueSource(strings = {"해산물파스타-0,레드-1", "타파-1,펩시콜라-2"})
    void createMenuByInvalidMenu(String menuValue) {
        assertThatThrownBy(() -> OrderedMenu.from(menuValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorHandler.INVALID_MENU.getException().getMessage());
    }

    @DisplayName("메뉴와 개수가 정상적으로 반환된다.")
    @ParameterizedTest(name = "[{index}] input {0}")
    @MethodSource("testMenu")
    void createMenu(OrderedMenu orderedMenu, Map<String, Integer> expectedMenu) {
        assertThat(orderedMenu.getOrderedMenu()).isEqualTo(expectedMenu);
    }
}
