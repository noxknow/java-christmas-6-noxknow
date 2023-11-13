package christmas.controller;

import christmas.domain.wrapper.EventDate;
import christmas.domain.wrapper.OrderedMenu;
import christmas.handler.InputHandler;
import christmas.handler.OutputHandler;

import java.util.Map;

public class EventController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public EventController(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void run() {
        EventDate eventDate = loadDate();

        OrderedMenu orderedMenu = loadOrderedMenu();

        showOrderedMenu(eventDate, orderedMenu);
    }

    private EventDate loadDate() {
        outputHandler.printGreetingMessage();

        while (true) {
            try {
                outputHandler.requestVisitDayMessage();
                String dateValue = inputHandler.inputValue();

                return EventDate.from(dateValue);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderedMenu loadOrderedMenu() {
        while (true) {
            try {
                outputHandler.requestMenuMessage();
                String menuValue = inputHandler.inputValue();

                return OrderedMenu.from(menuValue);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showOrderedMenu(EventDate eventDate, OrderedMenu orderedMenu) {
        int date = eventDate.getEventDate();
        Map<String, Integer> orderMenu = orderedMenu.getOrderedMenu();

        outputHandler.printOrderedMenu(date, orderMenu);
    }
}
