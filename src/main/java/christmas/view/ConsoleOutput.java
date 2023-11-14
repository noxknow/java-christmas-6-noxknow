package christmas.view;

import christmas.domain.DiscountResult;
import christmas.domain.MenuResult;
import christmas.handler.OutputHandler;

import java.util.Map;

import static christmas.handler.ConstantsHandler.*;

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
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
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
    public void printCostBeforeDiscount(MenuResult menuResult) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");

        int costBeforeDiscount = menuResult.calculateCostBeforeDiscount();
        String formattedBeforeCost = String.format("%,d원", costBeforeDiscount);
        System.out.println(formattedBeforeCost);

        printFreeGiftEvent(costBeforeDiscount);
    }

    private void printFreeGiftEvent(int costBeforeDiscount) {
        System.out.println();
        System.out.println("<증정 메뉴>");

        if (costBeforeDiscount >= MIN_AMOUNT_FOR_FREE_GIFT.getValue()) {
            System.out.println("샴페인 1개");
        } else if (costBeforeDiscount < MIN_AMOUNT_FOR_FREE_GIFT.getValue()) {
            System.out.println("없음");
        }
    }

    @Override
    public void printDiscountResult(DiscountResult discountResult, MenuResult menuResult) {
        System.out.println();
        System.out.println("<혜택 내역>");

        if (!checkApplyEvent(menuResult)) {
            boolean hasDiscount = hasDiscount(discountResult, menuResult);

            if (!hasDiscount) {
                System.out.println("없음");
            }
        } else if (checkApplyEvent(menuResult)) {
            System.out.println("없음");
        }
    }

    private boolean checkApplyEvent(MenuResult menuResult) {
        if (menuResult.calculateCostBeforeDiscount() < MIN_APPLY_EVENT_AMOUNT.getValue()) {
            return true;
        }

        return false;
    }

    private boolean hasDiscount(DiscountResult discountResult, MenuResult menuResult) {
        boolean hasDiscount = false;

        hasDiscount |= printChristmasDiscount(discountResult);
        hasDiscount |= printWeeklyDiscount(discountResult);
        hasDiscount |= printSpecialDiscount(discountResult);
        hasDiscount |= printEventDiscount(discountResult, menuResult);

        return hasDiscount;
    }

    private boolean printChristmasDiscount(DiscountResult discountResult) {
        int christmasDiscount = discountResult.christmasDiscount();

        if (christmasDiscount > INIT_VALUE.getValue()) {
            String formattedChristmas = String.format("크리스마스 디데이 할인: -%,d원", christmasDiscount);
            System.out.println(formattedChristmas);
            return true;
        }

        return false;
    }

    private boolean printWeeklyDiscount(DiscountResult discountResult) {
        int weeklyDiscount = discountResult.weeklyDiscount();
        String discountType = calculateType(discountResult);

        if (weeklyDiscount == INIT_VALUE.getValue()) {
            return false;
        }

        String formattedWeekly = String.format("%s: -%,d원",discountType, weeklyDiscount);
        System.out.println(formattedWeekly);

        return true;
    }

    private String calculateType(DiscountResult discountResult) {
        String discountType = "";

        if (discountResult.isWeekend()) {
            discountType = "주말 할인";
        } else if (!discountResult.isWeekend()) {
            discountType = "평일 할인";
        }

        return discountType;
    }

    private boolean printSpecialDiscount(DiscountResult discountResult) {
        int specialDiscount = discountResult.specialDiscount();

        if (specialDiscount > INIT_VALUE.getValue()) {
            String formattedSpecial = String.format("특별 할인: -%,d원", specialDiscount);
            System.out.println(formattedSpecial);
            return true;
        }

        return false;
    }

    private boolean printEventDiscount(DiscountResult discountResult, MenuResult menuResult) {
        int costBeforeDiscount = menuResult.calculateCostBeforeDiscount();
        int eventDiscount = discountResult.eventDiscount(costBeforeDiscount);

        if (eventDiscount > INIT_VALUE.getValue()) {
            String formattedEvent = String.format("증정 이벤트: -%,d원", eventDiscount);
            System.out.println(formattedEvent);
            return true;
        }

        return false;
    }

    @Override
    public void printTotalDiscount(DiscountResult discountResult, MenuResult menuResult) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        int totalDiscount = discountResult.totalDiscount(menuResult.calculateCostBeforeDiscount());

        if (!checkApplyEvent(menuResult)) {
            if (totalDiscount > INIT_VALUE.getValue()) {
                String formattedTotal = String.format("-%,d원", totalDiscount);
                System.out.println(formattedTotal);
            } else if (totalDiscount == INIT_VALUE.getValue()) {
                System.out.println("0원");
            }
        } else if (checkApplyEvent(menuResult)) {
            System.out.println("0원");
        }
    }

    @Override
    public void printTotalCost(int totalCost) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");

        String formattedTotalCost = String.format("%,d원", totalCost);
        System.out.println(formattedTotalCost);
    }

    @Override
    public void printEventBadge(String eventBadge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventBadge);
    }
}
