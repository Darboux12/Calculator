package com.calculator;

public class Subtract implements iPluginStructure {

    OperatorDataDirector director;

    OperatorDataBuilder subtractOperatorDataBuilder;

    OperatorData opData;

    Subtract() {

        this.director = new OperatorDataDirector();

        subtractOperatorDataBuilder = new AddOperatorDataBuilder();

        this.director.setOperatorBuilder(subtractOperatorDataBuilder);

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
        return a - b;
    }
}