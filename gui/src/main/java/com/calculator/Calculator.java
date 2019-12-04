package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calculator {

    private Calculate cal;

    private  String  expr;

    private static CalculatorFrame CalFrame;

    private static JTextField CalTextField;

    private static CalculatorButton b_0, b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8,b_9;

    private static CalculatorButton b_equal,b_add,b_sub,b_div,b_mul,b_reset;

    private static CalculatorButton b_frac, b_left_bracket, b_right_bracket;

    private  static CalculatorPanel CalPanelInput;

    private  static CalculatorPanel CalPanelText;

    private  ListenForButton CalListener;

    private  static CalculatorButton b_exit;

    private static Font CalTextFiledFont;

    public Calculator(){

        cal = new Calculate();

        expr = "";

        CalTextFiledFont = new Font("SansSerif", Font.BOLD, 20);

        CalFrame = new CalculatorFrame();
        CalTextField = new JTextField("",40);
        CalTextField.setEditable(false);
        CalTextField.setFont(CalTextFiledFont);
        CalTextField.setHorizontalAlignment(JTextField.CENTER);

        b_0 = new CalculatorButton("0");
        b_1 = new CalculatorButton("1");
        b_2 = new CalculatorButton("2");
        b_3 = new CalculatorButton("3");
        b_4 = new CalculatorButton("4");
        b_5 = new CalculatorButton("5");
        b_6 = new CalculatorButton("6");
        b_7 = new CalculatorButton("7");
        b_8 = new CalculatorButton("8");
        b_9 = new CalculatorButton("9");

        b_equal = new CalculatorButton("=");
        b_add = new CalculatorButton("+");
        b_sub = new CalculatorButton("-");
        b_div = new CalculatorButton("/");
        b_mul = new CalculatorButton("*");
        b_reset = new CalculatorButton("C");

        b_frac = new CalculatorButton(".");
        b_left_bracket = new CalculatorButton("(");
        b_right_bracket = new CalculatorButton(")");
        b_exit = new CalculatorButton("Exit");

        CalListener = new ListenForButton();

        CalPanelInput = new CalculatorPanel();
        CalPanelInput.setLayout(new GridLayout(4,5));

        CalPanelText = new CalculatorPanel();

        b_0.addActionListener(CalListener);
        b_1.addActionListener(CalListener);
        b_2.addActionListener(CalListener);
        b_3.addActionListener(CalListener);
        b_4.addActionListener(CalListener);
        b_5.addActionListener(CalListener);
        b_6.addActionListener(CalListener);
        b_7.addActionListener(CalListener);
        b_8.addActionListener(CalListener);
        b_9.addActionListener(CalListener);

        b_equal.addActionListener(CalListener);
        b_add.addActionListener(CalListener);
        b_sub.addActionListener(CalListener);
        b_div.addActionListener(CalListener);
        b_mul.addActionListener(CalListener);
        b_reset.addActionListener(CalListener);

        b_frac.addActionListener(CalListener);
        b_left_bracket.addActionListener(CalListener);
        b_right_bracket.addActionListener(CalListener);
        b_exit.addActionListener(CalListener);

        CalPanelInput.add(b_9);
        CalPanelInput.add(b_8);
        CalPanelInput.add(b_7);
        CalPanelInput.add(b_add);
        CalPanelInput.add(b_sub);
        CalPanelInput.add(b_6);
        CalPanelInput.add(b_5);
        CalPanelInput.add(b_4);
        CalPanelInput.add(b_mul);
        CalPanelInput.add(b_div);
        CalPanelInput.add(b_3);
        CalPanelInput.add(b_2);
        CalPanelInput.add(b_1);
        CalPanelInput.add(b_left_bracket);
        CalPanelInput.add(b_right_bracket);
        CalPanelInput.add(b_0);
        CalPanelInput.add(b_frac);
        CalPanelInput.add(b_reset);
        CalPanelInput.add(b_equal);
        CalPanelInput.add(b_exit);

        CalTextField.setSize(20,20);
        CalPanelText.setLayout(new BorderLayout());
        CalPanelText.add(CalTextField);
        CalFrame.setLayout(new GridLayout(2,0));
        CalFrame.add(CalPanelText);
        CalFrame.add(CalPanelInput);
        CalFrame.setSize(500, 500);
        CalFrame.setResizable(false);
        CalFrame.pack();
    }


    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == b_equal){

                expr = String.valueOf(cal.evaluate(expr));
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_0){
                expr += "0";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_1){
                expr += "1";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_2){
                expr += "2";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_3){
                expr += "3";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_4){
                expr += "4";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_5){
                expr += "5";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_6){
                expr += "6";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_7){
                expr += "7";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_8){
                expr += "8";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_9){
                expr += "9";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_reset){
                expr = "";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_frac){
                expr += ".";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_add){
                expr += " + ";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_sub){
                expr += " - ";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_mul){
                expr += " * ";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_div){
                expr += " / ";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_left_bracket){
                expr += "( ";
                CalTextField.setText(expr);
            }

            if(e.getSource() == b_right_bracket){
                expr += " )";
                CalTextField.setText(expr);
            }


            if(e.getSource() == b_exit){
                System.exit(0);
            }
        }
    }
}
