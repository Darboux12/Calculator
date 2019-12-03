package com.calculator.com.calculator.operatordata;

public class SubtractOperatorDataBuilder extends OperatorDataBuilder {

    @Override
    public void buildSign() {
        opData.setSign('-');
    }

    @Override
    public void buildName() {
        opData.setName("subtract");
    }

    @Override
    public void buildPrecedence() {
        opData.setPrecedence(1);
    }
}
