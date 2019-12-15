package com.plugins;

class DivideOperatorDataBuilder extends OperatorDataBuilder {

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

    @Override
    public void buildParamNumber() {
        opData.setOpParamNumber(2);
    }
}
