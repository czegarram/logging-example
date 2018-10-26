package com.czegarram.logging.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class LogValue {

    @Builder(builderMethodName = "customBuilder")
    public LogValue(String message, LogType logType){
        this.message = message;
        this.logType = logType;
    }

    private int id;
    private String message;
    private LogType logType;
}
