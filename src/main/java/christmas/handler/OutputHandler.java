package christmas.handler;

import java.util.Map;

public interface OutputHandler {

    void printGreetingMessage();
    void requestVisitDayMessage();
    void requestMenuMessage();
    void printOrderedMenu(int date, Map<String, Integer> orderMenu);
    void printBenefit();
}
