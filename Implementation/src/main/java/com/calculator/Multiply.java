package com.calculator;

public class Multiply implements iPluginStructure {

    private OperatorDataDirector director;
    private OperatorData opData;

    Multiply() {

        this.director = new OperatorDataDirector();
        this.setOperatorBuilder();
        this.director.constructOperatorData();
        this.opData = this.director.getOperatorData();
    }

    private void setOperatorBuilder(){
        this.director.setOperatorBuilder(new MultiplyOperatorDataBuilder());
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
