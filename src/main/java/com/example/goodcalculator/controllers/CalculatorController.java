package com.example.goodcalculator.controllers;

import com.example.goodcalculator.commands.Command;
import com.example.goodcalculator.models.Calculator;
import com.example.goodcalculator.views.CalculatorView;

import java.util.HashMap;
import java.util.Map;


/**
 * Controller component for handling user input and updating the model
 */
public class CalculatorController {
    private final Calculator model;
    private final CalculatorView view;
    private final Map<String, Command> commands = new HashMap<>();
    private Command currentCommand;

    public CalculatorController(Calculator model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void registerCommand(String symbol, Command command) {
        commands.put(symbol, command);
    }

    public void handleNumber(int number) {
        model.appendToDisplay(String.valueOf(number));
    }

    public void handleOperator(String symbol) {
        Command command = commands.get(symbol);
        if (command != null) {
            if (!model.isNewInput() && !model.isOperatorPressed()) {
                if (currentCommand != null) {
                    calculate();
                } else {
                    model.setStoredValue(model.getCurrentValue());
                }
            }
            currentCommand = command;
            model.setOperatorPressed(true);
        }
    }

    public void handleUnaryOperation(String symbol) {
        Command command = commands.get(symbol);
        if (command != null) {
            try {
                command.execute();
                model.setNewInput(true);
            } catch (ArithmeticException e) {
                model.setDisplayValue("Error");
                model.setNewInput(true);
                currentCommand = null;
            }
        }
    }

    public void calculate() {
        if (currentCommand != null && !model.isOperatorPressed()) {
            try {
                currentCommand.execute();
                model.setCurrentValue(model.getStoredValue());
                model.setNewInput(true);
                currentCommand = null;
            } catch (ArithmeticException e) {
                model.setDisplayValue("Error");
                model.setNewInput(true);
                currentCommand = null;
            }
        }
    }

    public void clear() {
        model.clear();
        currentCommand = null;
    }

    public String getDisplayValue() {
        return model.getDisplayValue();
    }

    public void memoryAdd() {
        model.storeInMemory();
    }

    public void memoryRecall() {
        model.setCurrentValue(model.recallMemory());
        model.setNewInput(true);
    }
}