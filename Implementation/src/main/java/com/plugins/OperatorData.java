package com.plugins;

public class OperatorData {

    private String sign;
    private String name;
    private int precedence;
    private int opParamNumber;

    OperatorData(){};

    public OperatorData(String op, String name, int prec,int param) {
        this.setSign(op);
        this.setName(name);
        this.setPrecedence(prec);
        this.setOpParamNumber(param);
    }

    public String sign() {
        return this.sign;
    }

    public String name() {
        return this.name;
    }

    public int precedence() {
        return this.precedence;
    }

    public int opParamNumber(){
        return this.opParamNumber;
    }

     void setSign(String sign){
        this.sign = sign;
    }

     void setName(String name){
        this.name = name;
    }

     void setPrecedence(int prece){
        this.precedence = prece;
    }

     void setOpParamNumber(int param){
        this.opParamNumber = param;
    }

}