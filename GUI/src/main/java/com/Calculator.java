package com;

import com.expectations.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

class Calculator {

     private Calculate cal;
     private String expr = "";
     private CalculatorButton[] Buttons;
     private CalculatorFrame CalFrame;
     private JTextField CalTextField;
     private CalculatorPanel CalPanelInput;
     private CalculatorPanel CalPanelText;
     private ListenForButton CalListener;
     private LogGenerator logGenerator;
     private StringWriter errors;
     private PrintWriter printErr;
     private String lastOp = " ";
     private int ExpecFlag;

     Calculator() {

         errors = new StringWriter();
         printErr = new PrintWriter(errors);

        this.cal = new Calculate();
        this.expr = "";
        this.Buttons = new CalculatorButton[getNumberOfButtons()];

        this.CalFrame = new CalculatorFrame();
        this.CalListener = new ListenForButton();
        this.CalPanelInput = new CalculatorPanel();
        this.CalPanelInput.setLayout(new GridLayout(5, 5));
        this.CalPanelText = new CalculatorPanel();

        this.setCalTextField();
        this.setButtons();
        this.setCalPanelText();
        this.setCalculatorFrame();

        this.logGenerator = new LogGenerator("GUI","GUI");
        this.logGenerator.sendNormalLog(Calculator.class.getName(),"Calculator GUI has been created");
    }

    private int getNumberOfButtons(){

        int i = 20 + this.cal.getPluginsCounter();

        while(i % 5 != 0)
            i++;

        return i;

    }

    private void setCalPanelText() {
        CalPanelText.setLayout(new BorderLayout());
        CalPanelText.add(CalTextField);
    }

    private void setCalTextField() {
        CalTextField = new JTextField("", 60);
        CalTextField.setEditable(false);
        CalTextField.setFont(new Font("SansSerif", Font.BOLD, 18));
        CalTextField.setHorizontalAlignment(JTextField.CENTER);
        CalTextField.setSize(20, 20);
    }

    private void setButtonsWithNames() {

        int i = 0;

        while (i < 10) {
            this.Buttons[i] = new CalculatorButton(Integer.toString(i));
            this.Buttons[i].addActionListener(CalListener);
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }

        for (Operator op : this.cal.getOperatorList().list) {

            this.Buttons[i] = new CalculatorButton((op.getOperatorName()) + " ( "
                                                    + op.getOperatorSign() + " ) ",op.getOperatorSign());
            this.Buttons[i].addActionListener(CalListener);
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }

        char[] OtherButtonNames = {'.', '(', ')', '=', 'C'};

        for (char c : OtherButtonNames) {
            this.Buttons[i] = new CalculatorButton(String.valueOf(c));
            this.Buttons[i].addActionListener(CalListener);
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }

        this.Buttons[i] = new CalculatorButton("Exit");
        this.Buttons[i].addActionListener(CalListener);
        this.CalPanelInput.add(this.Buttons[i]);
        i++;

        while(i < getNumberOfButtons()){
            this.Buttons[i] = new CalculatorButton(" ");
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }
    }

    private void setButtons() {

        int i = 0;

        while (i < 10) {
            this.Buttons[i] = new CalculatorButton(Integer.toString(i));
            this.Buttons[i].addActionListener(CalListener);
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }

        for (Operator op : this.cal.getOperatorList().list) {

            this.Buttons[i] = new CalculatorButton((op.getOperatorSign()));
            this.Buttons[i].addActionListener(CalListener);
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }

        char[] OtherButtonNames = {'.', '(', ')', '=', 'C'};

        for (char c : OtherButtonNames) {
            this.Buttons[i] = new CalculatorButton(String.valueOf(c));
            this.Buttons[i].addActionListener(CalListener);
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }

        this.Buttons[i] = new CalculatorButton("Exit");
        this.Buttons[i].addActionListener(CalListener);
        this.CalPanelInput.add(this.Buttons[i]);
        i++;

        while(i < getNumberOfButtons()){
            this.Buttons[i] = new CalculatorButton(" ");
            this.CalPanelInput.add(this.Buttons[i]);
            i++;
        }
    }

    private void setCalculatorFrame() {
        this.CalFrame.setLayout(new GridLayout(2, 0));
        this.CalFrame.add(CalPanelText);
        this.CalFrame.add(CalPanelInput);
        this.CalFrame.setSize(500, 500);
        this.CalFrame.setResizable(true);
        this.CalFrame.pack();
    }

    private boolean isInputExpressionCorrect(String expr){

             try{ this.cal.isExpressionCorrect(expr); }

             catch (NeighbouringOperatorsExpectation | WrongBracketsInput |
                     EmptyExpressionExpectation | NotCorrectExpressionExpectation |
                     WrongOperatorUsageExpectation | EmptyBracketExpectation |
                     WrongFractionInputExpectation  e){
                 e.printStackTrace(printErr);
                 logGenerator.sendExpectationLog(Calculate.class.getName(), errors.toString());
                 CalTextField.setText(e.getMessage());
                 ExpecFlag = 1;
                 return false;
             }

             return true;
    }

    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(ExpecFlag == 1){
                ExpecFlag = 0;
                expr = "";
            }

            for (CalculatorButton btn : Buttons) {
                if (e.getSource() == btn) {

                    if(btn.getText().equals("Exit")){
                        System.exit(0);
                    }

                    if(btn.getText().equals(".")){
                        expr += btn.getText();
                        CalTextField.setText(expr);
                        break;
                    }

                    if(btn.getText().equals(String.valueOf('='))){

                        if(isInputExpressionCorrect(expr)){
                            expr = cal.evaluate(expr) + " ";
                            CalTextField.setText(expr);
                            break;
                        }

                        else{
                            break;
                        }
                    }

                    if(btn.getText().equals(String.valueOf('C'))){
                        lastOp = " ";
                        expr = "";
                        CalTextField.setText(expr);
                        break;
                    }

                    if(Character.isDigit(btn.getText().charAt(0))){
                        lastOp = btn.getText();
                        expr += btn.getText();
                        CalTextField.setText(expr);
                        break;
                    }

                    if(btn.getButtonSign() == "-"){

                        if(lastOp == "("){
                            lastOp = btn.getButtonSign();
                            expr += " " + btn.getButtonSign();
                            CalTextField.setText(expr);
                            break;
                        }

                        for(Operator op : cal.getOperatorList().list){

                            if(lastOp.equals(op.getOperatorSign()) || lastOp.equals(" ")){

                                lastOp = btn.getButtonSign();

                                if(expr.isEmpty()){
                                    expr += btn.getButtonSign();
                                    CalTextField.setText(expr);
                                    break;
                                }

                                expr += " " + btn.getButtonSign();
                                CalTextField.setText(expr);
                                break;
                            }

                        }

                        if(lastOp.equals("-")) {
                            break;
                        }
                    }

                    if(expr.isEmpty()){
                        lastOp = btn.getButtonSign();
                        expr += btn.getButtonSign() + " ";
                        CalTextField.setText(expr);
                        break;
                    }

                    lastOp = btn.getButtonSign();
                    expr += " " + btn.getButtonSign() + " ";
                    CalTextField.setText(expr);
                }
            }
        }
    }
}

