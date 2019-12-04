package com.calculator;

public class MultiplyOperatorDataBuilder extends  OperatorDataBuilder {

    @Override
    public void buildSign() {
        opData.setSign('*');
    }

    @Override
    public void buildName() {
        opData.setName("multiply");
    }

    @Override
    public void buildPrecedence() {
        opData.setPrecedence(2);
    }
}
