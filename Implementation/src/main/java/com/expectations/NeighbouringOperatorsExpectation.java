package com.expectations;

public class NeighbouringOperatorsExpectation extends Exception {
   public NeighbouringOperatorsExpectation(){
        super("Two operators cannot be side by side");
    }
}
