package com.calculator;

import com.expectations.NeighbouringOperatorsExpectation;
import com.expectations.WrongBracketsInput;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

class Calculate {

    private OperatorsList OpList;
    private LogGenerator logGenerator;
    private int PluginsCounter;

    private StringWriter errors = new StringWriter();
    private PrintWriter  printErr = new PrintWriter(errors);

    Calculate() {

        this.OpList = new OperatorsList();
        this.logGenerator = new LogGenerator(Calculate.class.getName(),"Calculate");
        this.loadPlugins();
    }

    private void loadPlugins() {

        File PluginsPackage = new File("C:\\Users\\Dariusz\\Desktop\\Calculator\\plugins");

        File PluginsDirectory = new File                                                                                                ("C:\\Users\\Dariusz\\Desktop\\Calculator\\plugins\\com\\calculator");

        String PluginNames = "Loaded plugins: ";

        StringBuilder PluginNamesBuilder = new StringBuilder(PluginNames);

        Object PluginInstance;



        if (PluginsPackage.exists()) {

              logGenerator.sendNormalLog(Calculate.class.getName(),"Plugins Directory found");

              this.PluginsCounter = Objects.requireNonNull(PluginsDirectory.listFiles()).length;

              try {

                URL loadPath = PluginsPackage.toURI().toURL();
                URL[] classUrl = new URL[]{loadPath};
                ClassLoader cl = new URLClassLoader(classUrl);

                for (File PluginFile : Objects.requireNonNull(PluginsDirectory.listFiles())) {

                    PluginNamesBuilder.append(PluginFile.getName().replace(".class", ""));
                    PluginNamesBuilder.append(" ");

                    Class loadedClass = cl.loadClass("com.calculator." +                                                                PluginFile.getName().replace(".class", ""));

                    PluginInstance = loadedClass.newInstance();

                    iPluginStructure plugin = (iPluginStructure ) PluginInstance;

                    OpList.addOperator(plugin.getSign(), plugin.getOperatorName(),
                            plugin.getOperatorPrecedence(), plugin);
                }

            } catch (MalformedURLException | ClassNotFoundException |
                        IllegalAccessException | InstantiationException e){

                  e.printStackTrace(printErr);
                  logGenerator.sendExpectationLog(Calculate.class.getName(), errors.toString());
            }

             logGenerator.sendNormalLog(Calculate.class.getName(),PluginNamesBuilder.toString());

        } else{logGenerator.sendNormalLog(Calculate.class.getName(),"Plugins Directory not found"); }
    }

    private boolean isNumeric(String strNum) {
       return strNum.matches("-?\\d+(\\.\\d+)?");
   }

    private boolean isOperator(char op){

        for(Operator opElement : this.OpList.list)
            if(op == opElement.getOperatorSign())
                return true;

        return false;
    }

    private boolean hasPrecedence(char op1, char op2) {

        if (op2 == '(' || op2 == ')')
            return false;

        for(Operator op1Element : this.OpList.list)
            for(Operator op2Element : this.OpList.list)
                if(op1 == op1Element.getOperatorSign() && op2 == op2Element.getOperatorSign())
                    if(op1Element.getOperatorPrecedence() > op2Element.getOperatorPrecedence())
                        return false;

        return true;
    }

    private  double applyOp(char op, double b, double a) {

        for (Operator opElement : OpList.list) {
            if (op == opElement.getOperatorSign()) {

                try {

                    Method method = opElement.getOperatorFunction().getClass().getMethod("compute", double.class, double.class);

                    Object o = method.invoke(opElement.getOperatorFunction().getClass().newInstance(), a, b);

                    return ((Number) o).doubleValue();

                } catch (NoSuchMethodException | InstantiationException |
                            IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace(printErr);
                    logGenerator.sendExpectationLog(Calculate.class.getName(), errors.toString());
                }
            }
        }
        return -1;
    }

    double evaluate(String expression) {

        Stack<Double> values = new Stack<Double>();

        Stack<Character> ops = new Stack<Character>();

        for(String expr : expression.split("\\s+")){

            if (isNumeric(expr))
                values.push(Double.parseDouble(expr));

            else if (expr.charAt(0) == '(')
                ops.push(expr.charAt(0));

            else if (expr.charAt(0) == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            else if (isOperator(expr.charAt(0))) {

                while (!ops.empty() && hasPrecedence(expr.charAt(0), ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                ops.push(expr.charAt(0));
            }
        }


        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        double result = values.pop();

        logGenerator.sendNormalLog(Calculate.class.getName(),
                "Computing operation " + expression + " with result " + result);

        return result;
    }

    OperatorsList getOperatorList(){
        return this.OpList;
    }

    int getPluginsCounter(){
        return this.PluginsCounter;
    }

     boolean isExpressionCorrect(String expr){

        String [] ExpElements = expr.split("\\s+");

        for(int i=0; i<ExpElements.length-1; i++){

            try{

                if(!(isNumeric(ExpElements[i])) && !(isNumeric(ExpElements[i+1])) &&
                        ExpElements[i].charAt(0) != ')' && ExpElements[i+1].charAt(0) != '('
                )
                             throw new NeighbouringOperatorsExpectation();

                if(ExpElements[i].charAt(0) == ')' && ExpElements[i+1].charAt(0) == '(') {
                    throw new WrongBracketsInput();
                }
            }

              catch (NeighbouringOperatorsExpectation | WrongBracketsInput e){
                  e.printStackTrace(printErr);
                  logGenerator.sendExpectationLog(Calculate.class.getName(), errors.toString());
                  return false;
              }

        }









        return true;
    }
}
