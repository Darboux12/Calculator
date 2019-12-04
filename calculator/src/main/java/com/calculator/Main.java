package com.calculator;

public class Main {

    public static void main(String[] args) {

        Calculate c = new Calculate();

        c.loadPlugins();

        System.out.println( c.evaluate("(2+3) - 5 * (3 + 1)"));

    }
}
