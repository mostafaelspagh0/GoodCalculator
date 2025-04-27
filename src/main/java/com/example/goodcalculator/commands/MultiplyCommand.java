package com.example.goodcalculator.commands;

import com.example.goodcalculator.models.Calculator;

public class MultiplyCommand implements Command {
    private final Calculator calculator;

    public MultiplyCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.setStoredValue(calculator.getStoredValue() * calculator.getCurrentValue());
    }
}