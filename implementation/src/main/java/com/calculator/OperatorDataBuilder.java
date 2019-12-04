package com.calculator;

public abstract class OperatorDataBuilder {

    protected OperatorData opData;

    public OperatorData getOperatorData(){
        return this.opData;
    }

    public void createNewOperatorData(){
        this.opData = new OperatorData();
    }

    public abstract void buildSign();
    public abstract void buildName();
    public abstract void buildPrecedence();
}
