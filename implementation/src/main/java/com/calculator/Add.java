package com.calculator;


public class Add implements iPluginStructure {

    OperatorDataDirector director;

    OperatorDataBuilder addOperatorDataBuilder;

    OperatorData opData;

    Add() {

        this.director = new OperatorDataDirector();

        addOperatorDataBuilder = new AddOperatorDataBuilder();

        this.director.setOperatorBuilder(addOperatorDataBuilder);

        this.director.constructOperatorData();

        this.opData = this.director.getOperatorData();

    }

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

