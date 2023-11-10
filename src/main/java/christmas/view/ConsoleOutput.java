package christmas.view;

import christmas.handler.OutputHandler;

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
    public void printBenefitMessage() {

    }

    @Override
    public void printBenefit() {

    }
}
