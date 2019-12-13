package com.calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorButton extends JButton {

    public CalculatorButton(String name){
        super(name);
        setPreferredSize(new Dimension(40,40));
    }

}
