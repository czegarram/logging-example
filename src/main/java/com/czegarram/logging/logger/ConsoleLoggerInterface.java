package com.czegarram.logging.logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("console")
public class ConsoleLoggerInterface implements LoggerInterface {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConsoleLoggerInterface.class);

    public void info(String message){
        log.info(message);
    }
}
