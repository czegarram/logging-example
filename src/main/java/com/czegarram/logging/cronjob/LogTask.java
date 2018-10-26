package com.czegarram.logging.cronjob;


import com.czegarram.logging.service.JobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LogTask {

    private static final Logger log = LoggerFactory.getLogger(LogTask.class);

    @Autowired
    private JobLogger jobLogger;

    @Scheduled(fixedRate = 5000)
    public void logMessages() throws Exception{
        jobLogger.logMessage("Test", true, true,true);
    }
}
