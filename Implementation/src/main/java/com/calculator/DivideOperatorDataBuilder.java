package com.calculator;

public class DivideOperatorDataBuilder extends OperatorDataBuilder {

    @Override
    public void buildSign() {
        opData.setSign('/');
    }

    @Override
    public void buildName() {
        opData.setName("divide");
    }

    @Override
    public void buildPrecedence() {
        opData.setPrecedence(2);
    }
}
