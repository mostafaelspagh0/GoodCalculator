package com.example.goodcalculator.factories;

import com.example.goodcalculator.commands.*;
import com.example.goodcalculator.models.Calculator;

public class CommandFactory {
    private final Calculator calculator;

    public CommandFactory(Calculator calculator) {
        this.calculator = calculator;
    }

    public Command createAddCommand() {
        return new AddCommand(calculator);
    }

    public Command createSubtractCommand() {
        return new SubtractCommand(calculator);
    }

    public Command createMultiplyCommand() {
        return new MultiplyCommand(calculator);
    }

    public Command createDivideCommand() {
        return new DivideCommand(calculator);
    }

    public Command createSquareRootCommand() {
        return new SquareRootCommand(calculator);
    }

    public Command createPercentCommand() {
        return new PercentCommand(calculator);
    }
}