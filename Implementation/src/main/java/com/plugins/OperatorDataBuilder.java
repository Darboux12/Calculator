package com.plugins;

public abstract class OperatorDataBuilder {

     OperatorData opData;

     OperatorData getOperatorData(){
        return this.opData;
    }

     void createNewOperatorData(){
        this.opData = new OperatorData();
    }

    public abstract void buildSign();
    public abstract void buildName();
    public abstract void buildPrecedence();
    public abstract void buildParamNumber();
}
