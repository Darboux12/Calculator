package com.calculator;

public class Divide implements iPluginStructure {

    OperatorDataDirector director;

    OperatorDataBuilder divideOperatorDataBuilder;

    OperatorData opData;

    Divide() {

        this.director = new OperatorDataDirector();

        divideOperatorDataBuilder = new AddOperatorDataBuilder();

        this.director.setOperatorBuilder(divideOperatorDataBuilder);

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
        return a / b;
    }
}