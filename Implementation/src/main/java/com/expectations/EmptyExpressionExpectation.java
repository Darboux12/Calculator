package com.expectations;

public class EmptyExpressionExpectation extends Exception {
    public  EmptyExpressionExpectation(){
        super("Expression cannot be empty");
    }
}
