package com;

import com.expectations.NeighbouringOperatorsExpectation;
import com.expectations.WrongBracketsInput;
import com.plugins.iPluginStructure;

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

        File PluginsPackage = new File("C:\\Users\\Dariusz\\Desktop\\TO2019\\plugins");

        File PluginsDirectory = new File                                                                                                ("C:\\Users\\Dariusz\\Desktop\\TO2019\\plugins\\com\\plugins");

        String PluginNames = "Loaded plugins: ";

        StringBuilder PluginNamesBuilder = new StringBuilder(PluginNames);

        Object PluginInstance;



        if (PluginsPackage.exists()) {

              logGenerator.sendNormalLog(Calculate.class.getName(),"Plugins Directory found");

            this.PluginsCounter = PluginsDirectory.listFiles().length;

              try {

                URL loadPath = PluginsPackage.toURI().toURL();
                URL[] classUrl = new URL[]{loadPath};
                ClassLoader cl = new URLClassLoader(classUrl);

                for (File PluginFile : Objects.requireNonNull(PluginsDirectory.listFiles())) {

                    PluginNamesBuilder.append(PluginFile.getName().replace(".class", ""));
                    PluginNamesBuilder.append(" ");

                    Class loadedClass = cl.loadClass("com.plugins." +                                                                PluginFile.getName().replace(".class", ""));

                    PluginInstance = loadedClass.newInstance();

                    iPluginStructure plugin = (iPluginStructure ) PluginInstance;

                    OpList.addOperator(plugin.getSign(), plugin.getOperatorName(),
                            plugin.getOperatorPrecedence(),plugin.getOperatorParamNumber(),plugin);
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

    private boolean isOperator(String op){

        for(Operator opElement : this.OpList.list)
            if(op.equals(opElement.getOperatorSign()))
                return true;

        return false;
    }

    private boolean isTwoParamOperator(String op){

        for(Operator opElement : this.OpList.list)
            if(op.equals(opElement.getOperatorSign()))
                if (opElement.getOpParamNumber() == 2)
                        return true;

        return false;
    }

    private boolean isOneParamOperator(String op){

        for(Operator opElement : this.OpList.list)
            if(op.equals( opElement.getOperatorSign()))
                if (opElement.getOpParamNumber() == 1)
                    return true;

        return false;
    }

    private boolean hasPrecedence(String op1, String op2) {

        if (op2.equals("(")  || op2.equals(")"))
            return false;

        for(Operator op1Element : this.OpList.list)
            for(Operator op2Element : this.OpList.list)
                if(op1.equals(op1Element.getOperatorSign()) && op2.equals(op2Element.getOperatorSign()))
                    if(op1Element.getOperatorPrecedence() > op2Element.getOperatorPrecedence())
                        return false;

        return true;
    }

    private  double applyOp(String op, double b, double a) {

        for (Operator opElement : OpList.list) {
            if (op.equals(opElement.getOperatorSign())) {

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

        Stack<Double> values = new Stack<>();

        Stack<String> ops = new Stack<>();

        String opPeek;

        for(String expr : expression.split("\\s+")){

            if (isNumeric(expr))
                values.push(Double.parseDouble(expr));

            else if (expr.equals("("))
                ops.push(expr);

            else if (expr.equals(")")) {
                while (!ops.peek().equals("(")){

                    if(isTwoParamOperator(ops.peek()))
                         values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                    if(isOneParamOperator(ops.peek()))
                        values.push(applyOp(ops.pop(),0, values.pop()));
                }

                ops.pop();
            }

            else if (isOperator(expr)) {

                while (!ops.empty() && hasPrecedence(expr, ops.peek())){

                    //ta

                    opPeek = ops.peek();

                    if(isTwoParamOperator(opPeek))
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                    if(isOneParamOperator(opPeek)){

                        if(!values.empty())
                            values.push(applyOp(ops.pop(),0, values.pop()));
                        else
                            break;


                    }



                }

                ops.push(expr);
            }
        }


        while (!ops.empty()){

            opPeek = ops.peek();

            if(isTwoParamOperator(ops.peek()))
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));

            if(isOneParamOperator(opPeek))
                values.push(applyOp(ops.pop(), 0,values.pop()));
        }

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

    void isExpressionCorrect(String expr) throws
            NeighbouringOperatorsExpectation,WrongBracketsInput{

        String [] ExpElements = expr.split("\\s+");

        for(int i=0; i<ExpElements.length-1; i++){



                //Two Non-One-Param Operators side by side
                 if(isOperator(ExpElements[i]) && isOperator(ExpElements[i+1]) &&
                         (isOneParamOperator(ExpElements[i]) && !isOneParamOperator(ExpElements[i+1])) ||
                         (!isOneParamOperator(ExpElements[i]) && isOneParamOperator(ExpElements[i+1])) ||
                         (isTwoParamOperator(ExpElements[i]) && isTwoParamOperator(ExpElements[i+1])))
                                 throw new NeighbouringOperatorsExpectation();

                 // No numbers for two-param operator

         /*   if(!isNumeric(ExpElements[i]) && !ExpElements[i].equals(")")  ){
                throw new NeighbouringOperatorsExpectation();

            } */







            /*    if(!(isNumeric(ExpElements[i])) && !(isNumeric(ExpElements[i+1])) &&
                        ExpElements[i].charAt(0) != ')' && ExpElements[i+1].charAt(0) != '(')
                             throw new NeighbouringOperatorsExpectation(); */

                // Wrong bracket order )(
                if(ExpElements[i].charAt(0) == ')' && ExpElements[i+1].charAt(0) == '(') {
                    throw new WrongBracketsInput(); }








        }










    }
}
