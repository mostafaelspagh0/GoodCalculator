package com.example.goodcalculator.models;

public class Calculator {
    private double currentValue = 0;
    private double storedValue = 0;
    private double memory = 0;
    private boolean newInput = true;
    private boolean operatorPressed = false;
    private String displayValue = "0";

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String value) {
        this.displayValue = value;
    }

    public void appendToDisplay(String value) {
        if (newInput || operatorPressed) {
            displayValue = value;
            newInput = false;
            operatorPressed = false;
        } else {
            displayValue += value;
        }
    }

    public double getCurrentValue() {
        try {
            return Double.parseDouble(displayValue);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setCurrentValue(double value) {
        this.displayValue = formatNumber(value);
    }

    public double getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(double value) {
        this.storedValue = value;
    }

    public boolean isNewInput() {
        return newInput;
    }

    public void setNewInput(boolean value) {
        this.newInput = value;
    }

    public boolean isOperatorPressed() {
        return operatorPressed;
    }

    public void setOperatorPressed(boolean value) {
        this.operatorPressed = value;
    }

    public void clear() {
        currentValue = 0;
        storedValue = 0;
        displayValue = "0";
        newInput = true;
        operatorPressed = false;
    }

    public void storeInMemory() {
        memory += getCurrentValue();
    }

    public double recallMemory() {
        return memory;
    }

    public static String formatNumber(double number) {
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            return String.format("%.5f", number)
                    .replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
}