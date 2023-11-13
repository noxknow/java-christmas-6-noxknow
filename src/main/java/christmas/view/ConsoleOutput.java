package christmas.view;

import christmas.handler.OutputHandler;

import java.util.Map;

public class ConsoleOutput implements OutputHandler {

    @Override
    public void printGreetingMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void requestVisitDayMessage() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    @Override
    public void requestMenuMessage() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    @Override
    public void printOrderedMenu(int date, Map<String, Integer> orderMenu) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<주문 메뉴>");

        for (Map.Entry<String, Integer> entry : orderMenu.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();

            System.out.println(menu + " " + quantity + "개");
        }
    }

    @Override
    public void printBenefit() {

    }
}
