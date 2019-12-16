package com;

import javax.swing.*;
import java.awt.*;

public class CalculatorButton extends JButton {

    private String sign;

    CalculatorButton(String name, String sign){
        super(name);
        setSign(sign);
        setPreferredSize(new Dimension(40,40));
    }

    CalculatorButton(String name){
        super(name);
        setSign(name);
        this.setFont(new Font("SansSerif", Font.BOLD, 15));
        setPreferredSize(new Dimension(40,40));
    }

    private void setSign(String c){
        this.sign = c;
    }

    public String getButtonSign(){
        return this.sign;
    }
}
