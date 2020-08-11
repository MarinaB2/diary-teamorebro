package com.teamorebro.diaryteamorebro.utils;

import java.util.ArrayList;

public class Logger {

    private static Logger loggerInstance = null;

    private ArrayList<Command> log;

    public String getLogString(){
        StringBuilder out =  new StringBuilder();

        for(Command cmd : log){
            out.append(cmd.toString());
            out.append(System.getProperty("line.separator"));
        }

        return out.toString();
    }

    public ArrayList<Command> getLog(){
        return log;
    }

    public void logCommand(Command cmd){
        System.out.println(cmd.toString());
        log.add(cmd);
    }

    private Logger(){
        log = new ArrayList<Command>();
    }

    public static Logger getInstance(){
        if (loggerInstance == null){
            loggerInstance = new Logger();
        }

        return loggerInstance;
    }
}

