package com;

import javax.swing.*;
import java.awt.*;

public class CalculatorButton extends JButton {

    private char sign;

    CalculatorButton(String name, char sign){
        super(name);
        setSign(sign);
        setPreferredSize(new Dimension(40,40));
    }

    CalculatorButton(String name){
        super(name);
        setSign(name.charAt(0));
        setPreferredSize(new Dimension(40,40));
    }

    private void setSign(char c){
        this.sign = c;
    }

    public char getButtonSign(){
        return this.sign;
    }
}
