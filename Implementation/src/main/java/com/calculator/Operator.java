package com.calculator;

public class Operator {

   private char opSign;
   private String opName;
   private int opPrecedence;
   private Object opFunction;

    Operator(char sign, String name, int precedence, Object func){
        this.setOperatorSign(sign);
        this.setOperatorName(name);
        this.setOperatorPrecedence(precedence);
        this.setOperatorFunction(func);
    }

    private void setOperatorSign(char sign){
        this.opSign = sign;
    }

    private void setOperatorName(String name){
        this.opName = name;
    }

    private void setOperatorPrecedence(int precedence){
        this.opPrecedence = precedence;
    }

    private void setOperatorFunction(Object func){
        this.opFunction = func;
    }

    public char getOperatorSign(){
        return this.opSign;
    }

    public String getOperatorName(){
        return this.opName;
    }

    public int getOperatorPrecedence(){
        return this.opPrecedence;
    }

    public Object getOperatorFunction(){
        return this.opFunction;
    }
}
