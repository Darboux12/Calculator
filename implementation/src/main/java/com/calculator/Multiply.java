package com.calculator;

public class Multiply implements iPluginStructure {

    OperatorDataDirector director;

    OperatorDataBuilder multiplyOperatorDataBuilder;

    OperatorData opData;

    Multiply() {

        this.director = new OperatorDataDirector();

        multiplyOperatorDataBuilder = new AddOperatorDataBuilder();

        this.director.setOperatorBuilder(multiplyOperatorDataBuilder);

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
        return a * b;
    }
}
