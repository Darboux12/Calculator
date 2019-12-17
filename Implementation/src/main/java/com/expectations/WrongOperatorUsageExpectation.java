package com.expectations;

public class WrongOperatorUsageExpectation extends  Exception {
    public WrongOperatorUsageExpectation(){
        super("Wrong left or right side of the operator");
    }
}
