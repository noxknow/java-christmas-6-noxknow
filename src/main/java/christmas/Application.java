package christmas;

import christmas.controller.EventController;
import christmas.handler.InputHandler;
import christmas.handler.OutputHandler;
import christmas.view.ConsoleInput;
import christmas.view.ConsoleOutput;

public class Application {

    public static void main(String[] args) {

        final InputHandler inputHandler = new ConsoleInput();
        final OutputHandler outputHandler = new ConsoleOutput();

        new EventController(inputHandler, outputHandler).run();
    }
}
