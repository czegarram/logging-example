package com.czegarram.logging.util;

import com.czegarram.logging.dto.LogType;

import java.text.DateFormat;
import java.util.Date;

public class MessageUtil {

    public static String generateMessage(LogType severity, String messageText) throws Exception{

        if(severity == null || messageText == null || messageText.trim().isEmpty()){
            throw new Exception("Invalid parameters");
        }

        return severity.toString() +
                DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) +
                " " +
                messageText +
                "/n";
    }

}
