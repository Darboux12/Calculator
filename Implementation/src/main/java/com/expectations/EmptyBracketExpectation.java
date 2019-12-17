package com.expectations;

public class EmptyBracketExpectation  extends  Exception{
    public EmptyBracketExpectation(){
        super("Brackets cannot be empty");
    }
}
