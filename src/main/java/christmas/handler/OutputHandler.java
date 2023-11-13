package christmas.handler;

import christmas.domain.DiscountResult;
import christmas.domain.MenuResult;

import java.util.Map;

public interface OutputHandler {

    void printGreetingMessage();
    void requestVisitDayMessage();
    void requestMenuMessage();
    void printOrderedMenu(int date, Map<String, Integer> orderMenu);
    void printCostBeforeDiscount(MenuResult menuResult);
    void printDiscountResult(DiscountResult discountResult, MenuResult menuResult);
    void printTotalDiscount(DiscountResult discountResult, MenuResult menuResult);
    void printTotalCost(int totalCost);
}
