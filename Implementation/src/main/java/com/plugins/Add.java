package com.plugins;


public class Add implements iPluginStructure {

    private OperatorDataDirector director;
    private OperatorData opData;

   public Add(){

        this.director = new OperatorDataDirector();
        this.setOperatorBuilder();
        this.director.constructOperatorData();
        this.opData = this.director.getOperatorData();
    }

    private void setOperatorBuilder(){
        this.director.setOperatorBuilder(new AddOperatorDataBuilder());
    }

    public char getSign() {
        return this.opData.sign();
    }

    public String getOperatorName() {
        return this.opData.name();
    }

    public int getOperatorPrecedence() {
        return this.opData.precedence();
    }

    public double compute(double a, double b) {
        return a + b;
    }
}

