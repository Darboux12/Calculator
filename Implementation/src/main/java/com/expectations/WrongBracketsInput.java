package com.expectations;

public class WrongBracketsInput extends Exception {

   public WrongBracketsInput(){
        super(") bracket cannot be followed by ( bracket");
    }

}
