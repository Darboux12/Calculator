package com.plugins;

public interface iPluginStructure {
    String getSign();
    String getOperatorName();
    int getOperatorPrecedence();
    int getOperatorParamNumber();
    double compute(double a, double b);
}
