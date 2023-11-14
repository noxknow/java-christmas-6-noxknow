package christmas.controller;

import christmas.domain.DiscountResult;
import christmas.domain.MenuResult;
import christmas.domain.wrapper.EventDate;
import christmas.domain.wrapper.OrderedMenu;
import christmas.handler.InputHandler;
import christmas.handler.OutputHandler;

import java.util.Map;

import static christmas.handler.ConstantsHandler.*;

public class EventController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public EventController(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void run() {
        outputHandler.printGreetingMessage();

        EventDate eventDate = loadDate();

        OrderedMenu orderedMenu = loadOrderedMenu();

        previewBoard(eventDate, orderedMenu);
    }

    private void previewBoard(EventDate eventDate, OrderedMenu orderedMenu) {
        MenuResult menuResult = generateMenuResult(orderedMenu);
        DiscountResult discountResult = generateDiscountResult(eventDate, orderedMenu);

        showOrderedMenu(eventDate, orderedMenu);

        showCostBeforeDiscount(menuResult);

        showDiscountResult(menuResult, discountResult);

        showTotalDiscount(menuResult, discountResult);

        showTotalCost(menuResult, discountResult);

        showEventBadge(menuResult, discountResult);
    }

    private EventDate loadDate() {
        EventDate eventDate = null;

        while (eventDate == null) {
            try {
                outputHandler.requestVisitDayMessage();
                String dateValue = inputHandler.inputValue();
                eventDate = EventDate.from(dateValue);
            } catch (IllegalArgumentException e) {
                outputHandler.printError(e.getMessage());
            }
        }

        return eventDate;
    }

    private OrderedMenu loadOrderedMenu() {
        OrderedMenu orderedMenu = null;

        while (orderedMenu == null) {
            try {
                outputHandler.requestMenuMessage();
                String menuValue = inputHandler.inputValue();
                orderedMenu = OrderedMenu.from(menuValue);
            } catch (IllegalArgumentException e) {
                outputHandler.printError(e.getMessage());
            }
        }

        return orderedMenu;
    }

    private DiscountResult generateDiscountResult(EventDate eventDate, OrderedMenu orderedMenu) {
        return DiscountResult.of(eventDate.getEventDate(), orderedMenu.getOrderedMenu());
    }

    private MenuResult generateMenuResult(OrderedMenu orderedMenu) {
        return MenuResult.from(orderedMenu.getOrderedMenu());
    }

    private void showOrderedMenu(EventDate eventDate, OrderedMenu orderedMenu) {
        int date = eventDate.getEventDate();
        Map<String, Integer> orderMenu = orderedMenu.getOrderedMenu();

        outputHandler.printOrderedMenu(date, orderMenu);
    }

    private void showCostBeforeDiscount(MenuResult menuResult) {
        outputHandler.printCostBeforeDiscount(menuResult);
    }

    private void showDiscountResult(MenuResult menuResult, DiscountResult discountResult) {
        outputHandler.printDiscountResult(discountResult, menuResult);
    }

    private void showTotalDiscount(MenuResult menuResult, DiscountResult discountResult) {
        outputHandler.printTotalDiscount(discountResult, menuResult);
    }

    private void showTotalCost(MenuResult menuResult, DiscountResult discountResult) {
        int costBeforeDiscount = menuResult.calculateCostBeforeDiscount();
        int totalDiscount = discountResult.totalDiscount(costBeforeDiscount);
        int totalCost = INIT_VALUE.getValue();

        if (costBeforeDiscount < MIN_APPLY_EVENT_AMOUNT.getValue()) {
            totalCost = costBeforeDiscount;
        } else if (costBeforeDiscount >= MIN_AMOUNT_FOR_FREE_GIFT.getValue()) {
            totalCost = costBeforeDiscount - totalDiscount + CHAMPAGNE_AMOUNT.getValue();
        } else if (costBeforeDiscount < MIN_AMOUNT_FOR_FREE_GIFT.getValue()) {
            totalCost = costBeforeDiscount - totalDiscount;
        }

        outputHandler.printTotalCost(totalCost);
    }

    private void showEventBadge(MenuResult menuResult, DiscountResult discountResult) {
        int costBeforeDiscount = menuResult.calculateCostBeforeDiscount();
        int totalDiscount = discountResult.totalDiscount(costBeforeDiscount);

        if (costBeforeDiscount < MIN_APPLY_EVENT_AMOUNT.getValue()) {
            totalDiscount = INIT_VALUE.getValue();
        }

        String eventBadge = discountResult.eventBadge(totalDiscount);

        outputHandler.printEventBadge(eventBadge);
    }
}
