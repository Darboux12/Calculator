package com.calculator;

public class OperatorData {
    private char sign;
    private String name;
    private int precedence;

    public OperatorData(){};

    public OperatorData(char op, String name, int prec) {
        this.sign = op;
        this.name = name;
        this.precedence = prec;
    }

    public char sign() {
        return this.sign;
    }

    public String name() {
        return this.name;
    }

    public int precedence() {
        return this.precedence;
    }

    public void setSign(char sign){
        this.sign = sign;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrecedence(int prece){
        this.precedence = prece;
    }

}