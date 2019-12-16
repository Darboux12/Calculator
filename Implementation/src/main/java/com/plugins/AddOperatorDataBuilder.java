package com.plugins;

class AddOperatorDataBuilder extends OperatorDataBuilder {

    @Override
    public void buildSign() {
        opData.setSign("+");
    }

    @Override
    public void buildName() {
        opData.setName("add");
    }

    @Override
    public void buildPrecedence() {
        opData.setPrecedence(1);
    }

    @Override
    public void buildParamNumber() {
        opData.setOpParamNumber(2);
    }
}
