package com.calculator.com.calculator.operatordata;

public class AddOperatorDataBuilder extends OperatorDataBuilder{

    @Override
    public void buildSign() {
        opData.setSign('+');
    }

    @Override
    public void buildName() {
        opData.setName("add");
    }

    @Override
    public void buildPrecedence() {
        opData.setPrecedence(1);
    }
}
