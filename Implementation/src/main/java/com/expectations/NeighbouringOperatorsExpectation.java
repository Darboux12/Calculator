package com.expectations;

public class NeighbouringOperatorsExpectation extends Exception {
   public NeighbouringOperatorsExpectation(){
        super("Two non-one-parameter operators cannot be side by side");
    }
}
