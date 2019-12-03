package com.calculator;

public class Divide implements iPluginStructure {

    OperatorData opData;

    Divide() { this.opData = new OperatorData('/', "divide", 2); }

    public char getSign() {
        return this.opData.sign();
    }

    public String getOperatorName() {
        return this.opData.name();
    }

    public int getOperatorPrecedence() {
        return this.opData.precedence();
    }

    public double compute(double a, double b) {
        return a / b;
    }
}