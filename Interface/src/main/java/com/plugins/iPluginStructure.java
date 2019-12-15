package com.plugins;

public interface iPluginStructure {
    char getSign();
    String getOperatorName();
    int getOperatorPrecedence();
    double compute(double a, double b);
}
