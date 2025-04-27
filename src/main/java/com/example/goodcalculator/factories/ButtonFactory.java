package com.example.goodcalculator.factories;

import javafx.scene.control.Button;

public class ButtonFactory {
    public Button createNumberButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button button = new Button(text);
        button.setPrefSize(50, 50);
        button.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: #000000; -fx-font-size: 14px;");
        button.setOnAction(handler);
        return button;
    }

    public Button createOperatorButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button button = new Button(text);
        button.setPrefSize(50, 50);
        button.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #ffffff; -fx-font-size: 14px;");
        button.setOnAction(handler);
        return button;
    }
}