package com.calculator;

public class Operator {

    char opSign;
    String opName;
    int opPrecedence;
    Object opFunction;

    Operator(char sign, String name, int precedence, Object func){
        this.opSign = sign;
        this.opName = name;
        this.opPrecedence = precedence;
        this.opFunction = func;
    }
}
