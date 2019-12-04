package com.calculator;

import java.util.ArrayList;
import java.util.List;

public class OperatorsList {

    List<Operator> list = new ArrayList<Operator>();

    OperatorsList(){
        this.list.add(new Operator('+',"add",1,new Add()));
        this.list.add(new Operator('-',"subtract",1,new Subtract()));
        this.list.add(new Operator('*',"multiply",2,new Multiply()));
        this.list.add(new Operator('/',"divide",2,new Divide()));
    }

    public void addOperator(char sign, String name, int prededence, Object obj){
        this.list.add(new Operator(sign,name,prededence,obj));
    }

}
