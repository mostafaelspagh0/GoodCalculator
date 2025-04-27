package com.example.goodcalculator.commands;


import com.example.goodcalculator.models.Calculator;

public class DivideCommand implements Command {
    private final Calculator calculator;

    public DivideCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        if (calculator.getCurrentValue() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        calculator.setStoredValue(calculator.getStoredValue() / calculator.getCurrentValue());
    }
}