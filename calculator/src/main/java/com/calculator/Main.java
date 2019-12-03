package com.calculator;

public class Main {

    public static void main(String[] args) {

        Calculate c = new Calculate();

        c.loadPlugins();

        System.out.println( c.evaluate("27 # 3"));

    }
}
