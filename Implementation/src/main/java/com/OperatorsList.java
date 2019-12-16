package com;

import com.plugins.Add;
import com.plugins.Divide;
import com.plugins.Multiply;
import com.plugins.Subtract;

import java.util.ArrayList;
import java.util.List;

public class OperatorsList {

    List<Operator> list = new ArrayList<Operator>();

    OperatorsList(){
        this.list.add(new Operator("+","add",1,2,new Add()));
        this.list.add(new Operator("-","subtract",1,2,new Subtract()));
        this.list.add(new Operator("*","multiply",2,2,new Multiply()));
        this.list.add(new Operator("/","divide",2,2,new Divide()));
    }

    public void addOperator(String sign, String name, int prededence,int param, Object obj){
        this.list.add(new Operator(sign,name,prededence,param,obj));
    }



}
