package com.expectations;

public class WrongFractionInputExpectation extends  Exception{
    public WrongFractionInputExpectation(){
        super("Input fraction is not correct");
    }
}
