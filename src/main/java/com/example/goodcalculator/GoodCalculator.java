package com.example.goodcalculator;

import com.example.goodcalculator.controllers.CalculatorController;
import com.example.goodcalculator.di.DIContainer;
import com.example.goodcalculator.factories.CommandFactory;
import com.example.goodcalculator.models.Calculator;
import com.example.goodcalculator.views.CalculatorView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GoodCalculator extends Application {
    @Override
    public void start(Stage primaryStage) {

        DIContainer container = new DIContainer();

        container.register(Calculator.class, new Calculator());
        container.register(CalculatorView.class, new CalculatorView());
        container.register(CalculatorController.class, new CalculatorController(container.resolve(Calculator.class), container.resolve(CalculatorView.class)));
        container.register(CommandFactory.class, new CommandFactory(container.resolve(Calculator.class)));


        Calculator model = container.resolve(Calculator.class);
        CalculatorView view = container.resolve(CalculatorView.class);
        CalculatorController controller = container.resolve(CalculatorController.class);
        CommandFactory commandFactory = container.resolve(CommandFactory.class);


        controller.registerCommand("+", commandFactory.createAddCommand());
        controller.registerCommand("-", commandFactory.createSubtractCommand());
        controller.registerCommand("*", commandFactory.createMultiplyCommand());
        controller.registerCommand("/", commandFactory.createDivideCommand());
        controller.registerCommand("sqrt", commandFactory.createSquareRootCommand());
        controller.registerCommand("%", commandFactory.createPercentCommand());


        Scene scene = view.createScene(controller);
        primaryStage.setTitle("Pattern-Based Calculator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}