package com.calculator;


import com.calculator.com.calculator.operatordata.AddOperatorDataBuilder;
import com.calculator.com.calculator.operatordata.OperatorData;
import com.calculator.com.calculator.operatordata.OperatorDataBuilder;
import com.calculator.com.calculator.operatordata.OperatorDataDirector;

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

