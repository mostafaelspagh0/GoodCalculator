package com.example.goodcalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BadCalculator extends Application {
    private TextField display;
    private double num1 = 0;
    private double memory = 0;
    private String operator = "";
    private boolean start = true;
    private boolean operatorPressed = false;

    @Override
    public void start(Stage primaryStage) {
        // Setup display
        display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setStyle("-fx-font-size: 18px;");

        // Create grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);

        // Create number buttons
        for (int i = 0; i < 10; i++) {
            final int number = i;
            Button button = new Button(String.valueOf(i));
            button.setPrefSize(50, 50);
            button.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: #000000; -fx-font-size: 14px;");
            button.setOnAction(e -> {
                if (start || operatorPressed) {
                    display.setText("");
                    start = false;
                    operatorPressed = false;
                }
                display.setText(display.getText() + number);
            });

            // Position numbers in grid
            if (i == 0) {
                grid.add(button, 1, 4);
            } else {
                grid.add(button, (i - 1) % 3, 3 - (i - 1) / 3);
            }
        }

        // Create operator buttons
        Button addBtn = createOperatorButton("+");
        grid.add(addBtn, 3, 3);

        Button subtractBtn = createOperatorButton("-");
        grid.add(subtractBtn, 3, 2);

        Button multiplyBtn = createOperatorButton("*");
        grid.add(multiplyBtn, 3, 1);

        Button divideBtn = createOperatorButton("/");
        grid.add(divideBtn, 3, 0);

        // Create equals button
        Button equalsBtn = new Button("=");
        equalsBtn.setPrefSize(50, 50);
        equalsBtn.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        equalsBtn.setOnAction(e -> {
            calculate();
        });
        grid.add(equalsBtn, 3, 4);

        // Create clear button
        Button clearBtn = new Button("C");
        clearBtn.setPrefSize(50, 50);
        clearBtn.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        clearBtn.setOnAction(e -> {
            display.setText("0");
            operator = "";
            start = true;
            operatorPressed = false;
        });
        grid.add(clearBtn, 0, 4);

        // Create square root button
        Button sqrtBtn = new Button("√");
        sqrtBtn.setPrefSize(50, 50);
        sqrtBtn.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        sqrtBtn.setOnAction(e -> {
            if (!display.getText().isEmpty()) {
                double value = Double.parseDouble(display.getText());
                if (value < 0) {
                    display.setText("Error");
                } else {
                    double result = Math.sqrt(value);
                    display.setText(formatNumber(result));
                }
                start = true;
            }
        });
        grid.add(sqrtBtn, 2, 4);

        // Create percentage button
        Button percentBtn = new Button("%");
        percentBtn.setPrefSize(50, 50);
        percentBtn.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        percentBtn.setOnAction(e -> {
            if (!display.getText().isEmpty() && !operator.isEmpty()) {
                double value = Double.parseDouble(display.getText());
                // Apply percentage as a calculation relative to first number
                display.setText(formatNumber(num1 * value / 100));
            }
        });
        grid.add(percentBtn, 0, 0);

        // Create memory buttons
        Button memoryAddBtn = new Button("M+");
        memoryAddBtn.setPrefSize(50, 50);
        memoryAddBtn.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        memoryAddBtn.setOnAction(e -> {
            if (!display.getText().isEmpty() && !display.getText().equals("Error")) {
                memory += Double.parseDouble(display.getText());
            }
        });
        grid.add(memoryAddBtn, 1, 0);

        Button memoryRecallBtn = new Button("MR");
        memoryRecallBtn.setPrefSize(50, 50);
        memoryRecallBtn.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        memoryRecallBtn.setOnAction(e -> {
            display.setText(formatNumber(memory));
            start = true;
        });
        grid.add(memoryRecallBtn, 2, 0);

        // Set up scene
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(display, grid);
        Scene scene = new Scene(root, 260, 320);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Button createOperatorButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(50, 50);
        button.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        button.setOnAction(e -> {
            if (!display.getText().isEmpty() && !display.getText().equals("Error")) {
                if (!operator.isEmpty() && !operatorPressed) {
                    calculate();
                } else {
                    num1 = Double.parseDouble(display.getText());
                }
                operator = text;
                operatorPressed = true;
            }
        });
        return button;
    }

    private void calculate() {
        if (!operator.isEmpty() && !operatorPressed) {
            double num2 = Double.parseDouble(display.getText());
            double result = 0;

            // Perform calculation based on operator
            try {
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error");
                            operator = "";
                            start = true;
                            return;
                        }
                        break;
                }

                display.setText(formatNumber(result));
                num1 = result;
                operator = "";
                start = true;
            } catch (Exception ex) {
                display.setText("Error");
                operator = "";
                start = true;
            }
        }
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            return String.format("%.5f", number).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}