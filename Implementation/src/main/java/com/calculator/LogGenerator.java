package com.calculator;

import java.io.IOException;
import java.text.MessageFormat;;
import java.util.Date;
import java.util.logging.*;

public class LogGenerator{

    private Logger CalculatorLogger;
    private  FileHandler CalculatorFileHandler;

    LogGenerator(String LoggerName,String FileName){

        try{
            CalculatorLogger =  Logger.getLogger(LoggerName);
            CalculatorFileHandler = new FileHandler(FileName + ".log", true);
            this.setFileHandlerFormatter();
            CalculatorLogger.addHandler(CalculatorFileHandler);
            CalculatorLogger.setUseParentHandlers(false);
        }
        catch(IOException e){
            System.out.println("IOException");
        }
    }

    private void setFileHandlerFormatter(){

        this.CalculatorFileHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return
                                "Source Method: "+ record.getSourceClassName().substring(
                                record.getSourceClassName().lastIndexOf(".")+1,
                                record.getSourceClassName().length())
                                + "."
                                + record.getSourceMethodName()
                                + "() " + "\n"
                                + record.getMessage();
            }
        });
    }

    public void sendNormalLog(String className,String message){

      String msg = "Sent at {0,time} on {0,date} \n" +
                   "Send from: {1} \n" +
                   "Message: {2} \n" +
                   "########################################################\n";


      MessageFormat msgFormat = new MessageFormat(msg);

      this.CalculatorLogger.info(msgFormat.format(new Object[] {new Date(), className, message} ));
    }

    public void sendExpectationLog(String className,String expectation){

        String msg = "Sent at {0,time} on {0,date} \n" +
                "Send from: {1} \n" +
                "Expectation thrown: \n{2}" +
                "########################################################";

        MessageFormat msgFormat = new MessageFormat(msg);

        this.CalculatorLogger.info(msgFormat.format(new Object[] {new Date(), className,expectation} ));
    }
}
