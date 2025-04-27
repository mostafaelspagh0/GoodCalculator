package com.example.goodcalculator.views;

import com.example.goodcalculator.controllers.CalculatorController;
import com.example.goodcalculator.factories.ButtonFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * View component for the calculator UI
 */
public class CalculatorView {
    private TextField display;
    private final ButtonFactory buttonFactory;

    public CalculatorView() {
        display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setStyle("-fx-font-size: 18px;");

        buttonFactory = new ButtonFactory();
    }

    public Scene createScene(CalculatorController controller) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);

        // Create number buttons
        for (int i = 0; i < 10; i++) {
            final int number = i;
            Button button = buttonFactory.createNumberButton(String.valueOf(i), e -> {
                controller.handleNumber(number);
                updateDisplay(controller.getDisplayValue());
            });

            // Position numbers in grid
            if (i == 0) {
                grid.add(button, 1, 4);
            } else {
                grid.add(button, (i - 1) % 3, 3 - (i - 1) / 3);
            }
        }

        // Create operator buttons
        String[] operations = {"+", "-", "*", "/"};
        int[] posX = {3, 3, 3, 3};
        int[] posY = {3, 2, 1, 0};

        for (int i = 0; i < operations.length; i++) {
            final String op = operations[i];
            Button button = buttonFactory.createOperatorButton(op, e -> {
                controller.handleOperator(op);
                updateDisplay(controller.getDisplayValue());
            });
            grid.add(button, posX[i], posY[i]);
        }

        // Equals button
        Button equalsBtn = buttonFactory.createOperatorButton("=", e -> {
            controller.calculate();
            updateDisplay(controller.getDisplayValue());
        });
        grid.add(equalsBtn, 3, 4);

        // Clear button
        Button clearBtn = buttonFactory.createOperatorButton("C", e -> {
            controller.clear();
            updateDisplay(controller.getDisplayValue());
        });
        grid.add(clearBtn, 0, 4);

        // Square root button
        Button sqrtBtn = buttonFactory.createOperatorButton("√", e -> {
            controller.handleUnaryOperation("sqrt");
            updateDisplay(controller.getDisplayValue());
        });
        grid.add(sqrtBtn, 2, 4);

        // Percentage button
        Button percentBtn = buttonFactory.createOperatorButton("%", e -> {
            controller.handleOperator("%");
            updateDisplay(controller.getDisplayValue());
        });
        grid.add(percentBtn, 0, 0);

        // Memory buttons
        Button memoryAddBtn = buttonFactory.createOperatorButton("M+", e -> {
            controller.memoryAdd();
            updateDisplay(controller.getDisplayValue());
        });
        grid.add(memoryAddBtn, 1, 0);

        Button memoryRecallBtn = buttonFactory.createOperatorButton("MR", e -> {
            controller.memoryRecall();
            updateDisplay(controller.getDisplayValue());
        });
        grid.add(memoryRecallBtn, 2, 0);

        root.getChildren().addAll(display, grid);
        return new Scene(root, 260, 320);
    }

    public void updateDisplay(String value) {
        display.setText(value);
    }
}