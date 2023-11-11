package christmas.domain.wrapper;

import christmas.handler.ErrorHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class OrderedMenuTest {

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
}
