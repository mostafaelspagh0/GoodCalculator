package com.example.goodcalculator.commands;

import com.example.goodcalculator.models.Calculator;

public class SquareRootCommand implements Command {
    private final Calculator calculator;

    public SquareRootCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        double value = calculator.getCurrentValue();
        if (value < 0) {
            throw new ArithmeticException("Cannot take square root of negative number");
        }
        calculator.setStoredValue(Math.sqrt(value));
    }
}