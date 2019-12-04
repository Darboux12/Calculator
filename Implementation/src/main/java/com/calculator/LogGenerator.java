package com.calculator;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogGenerator {

    private Logger CalculatorLogger;

    LogGenerator(){

        CalculatorLogger =  Logger.getLogger("Calculator");

        FileHandler CalculatorFileHandler;

        try{

            CalculatorFileHandler = new FileHandler("calculatorLogs.log", true);

            CalculatorLogger.addHandler(CalculatorFileHandler);

            CalculatorLogger.setUseParentHandlers(false);

            SimpleFormatter formatter = new SimpleFormatter();

            CalculatorFileHandler.setFormatter(formatter);


        }

        catch(IOException e){
            System.out.println("IOException");
        }


    }

    public void addInfo(String str){
        this.CalculatorLogger.info(str);
    }

    public void addWarning(String str){
        this.CalculatorLogger.warning(str);
    }

}
