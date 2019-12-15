package com.plugins;

 class OperatorDataDirector {

    private OperatorDataBuilder opBuilder;

    void setOperatorBuilder(OperatorDataBuilder op){
        this.opBuilder = op;
    }

    OperatorData getOperatorData(){
        return this.opBuilder.getOperatorData();
    }

    void constructOperatorData(){

        this.opBuilder.createNewOperatorData();
        this.opBuilder.buildSign();
        this.opBuilder.buildName();
        this.opBuilder.buildPrecedence();


    }
}
