package com.example.goodcalculator.commands;

import com.example.goodcalculator.models.Calculator;

public class PercentCommand implements Command {
    private final Calculator calculator;

    public PercentCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.setStoredValue(calculator.getStoredValue() * (calculator.getCurrentValue() / 100.0));
    }
}