package com.plugins;

public class OperatorDataDirector {

    private OperatorDataBuilder opBuilder;

    public void setOperatorBuilder(OperatorDataBuilder op){
        this.opBuilder = op;
    }

    public OperatorData getOperatorData(){
        return this.opBuilder.getOperatorData();
    }

    public void constructOperatorData(){

        this.opBuilder.createNewOperatorData();
        this.opBuilder.buildSign();
        this.opBuilder.buildName();
        this.opBuilder.buildPrecedence();


    }
}
