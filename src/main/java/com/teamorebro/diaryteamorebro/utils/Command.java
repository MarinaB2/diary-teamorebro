package com.teamorebro.diaryteamorebro.utils;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Command {

    public Date date = new Date();
    public HttpStatus result;

    public String requestMethod = "UNKNOWN";
    public String requestIP = "UNKNOWN";
    public String requestPath = "UNKNOWN";

    public Command(HttpServletRequest request){

        this.requestIP = request.getRemoteAddr();
        this.requestPath = request.getRequestURI();
        this.requestMethod = request.getMethod();

    }

    public void setResult(HttpStatus result){
        this.result = result;
    }

    public Integer something()
    {
        return null;
    }


    public String toString(){
        String out = "";

        //date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        out += (String.format("%1$-25s",formatter.format(date)));

        //IP
        out += (String.format("%1$-20s",requestIP));

        //request
        out += (String.format("%1$-10s",requestMethod));

        //request
        out += (String.format("%1$-30s",requestPath));

        //result
        out += (String.format("%1$-10s",result));

        return out.toString();
    }
}
