package christmas.controller;

import christmas.domain.wrapper.EventDate;
import christmas.handler.InputHandler;
import christmas.handler.OutputHandler;

public class EventController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public EventController(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void run() {
        EventDate eventDate = loadDate();
    }

    private EventDate loadDate() {
        outputHandler.printGreetingMessage();

        while (true) {
            try {
                outputHandler.requestVisitDayMessage();
                String dateString = inputHandler.inputValue();

                return EventDate.from(dateString);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
