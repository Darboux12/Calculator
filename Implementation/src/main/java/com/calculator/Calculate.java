package com.calculator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Calculate {

    private OperatorsList OpList = new OperatorsList();

    LogGenerator logGenerator = new LogGenerator();

    int PluginsCounter;

    public Calculate() {
        this.loadPlugins();
    }

    public void loadPlugins() {

        File PluginsPackage = new File("C:\\Users\\Dariusz\\Desktop\\Calculator\\plugins");

        File PluginsDirectory = new File                                                                                                ("C:\\Users\\Dariusz\\Desktop\\Calculator\\plugins\\com\\calculator");

        String PluginNames = "Loaded plugins: ";

        StringBuilder PluginNamesBuilder = new StringBuilder(PluginNames);

        Object PluginInstance;

        if (PluginsPackage.exists()) {

               logGenerator.addInfo("Plugins Directory found");

               this.PluginsCounter = PluginsDirectory.listFiles().length;

            try {
                URL loadPath = PluginsPackage.toURI().toURL();
                URL[] classUrl = new URL[]{loadPath};
                ClassLoader cl = new URLClassLoader(classUrl);

                for (File PluginFile : PluginsDirectory.listFiles()) {

                    PluginNamesBuilder.append(PluginFile.getName().replace(".class", ""));
                    PluginNamesBuilder.append(" ");

                    Class loadedClass = cl.loadClass("com.calculator." +                                                                PluginFile.getName().replace(".class", ""));

                    PluginInstance = loadedClass.newInstance();

                    iPluginStructure plugin = (iPluginStructure ) PluginInstance;

                    OpList.addOperator(plugin.getSign(), plugin.getOperatorName(),
                            plugin.getOperatorPrecedence(), plugin);

                }

            } catch (MalformedURLException | ClassNotFoundException e) {
                e.printStackTrace();
                logGenerator.addWarning(e.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logGenerator.addWarning(e.toString());
            } catch (InstantiationException e) {
                e.printStackTrace();
                logGenerator.addWarning(e.toString());
            }

            logGenerator.addInfo(PluginNamesBuilder.toString());

        } else{logGenerator.addWarning("Plugins Directory not found"); }

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

                } catch (NoSuchMethodException e) {
                    logGenerator.addInfo(e.getStackTrace().toString());

                } catch (InstantiationException e) {
                    logGenerator.addInfo(e.getStackTrace().toString());

                } catch (IllegalAccessException e) {
                    logGenerator.addInfo(e.getStackTrace().toString());

                } catch (InvocationTargetException e) {
                    logGenerator.addInfo(e.getStackTrace().toString());

                }

            }

        }



        return -1;
    }

    public  double evaluate(String expression) {

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

        logGenerator.addInfo("Computing operation " + expression + " with result " + result);




        return result;
    }

    public OperatorsList getOperatorList(){
        return this.OpList;
    }

    public int getPluginsCounter(){
        return this.PluginsCounter;
    }

}
