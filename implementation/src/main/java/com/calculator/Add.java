package com.calculator;


public class Add implements iPluginStructure {

    OperatorData  opData;

    Add() { this.opData = new OperatorData('+', "add", 1); }

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
        return a + b;
    }
}

