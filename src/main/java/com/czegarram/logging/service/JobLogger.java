package com.czegarram.logging.service;

import com.czegarram.logging.dto.LogType;
import com.czegarram.logging.dto.LogValue;
import com.czegarram.logging.logger.LoggerInterface;
import com.czegarram.logging.mapper.LogValueMapper;
import com.czegarram.logging.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JobLogger {


    @Autowired
    private LogValueMapper logValueMapper;

    @Autowired
    @Qualifier("file")
    private LoggerInterface fileLogger;

    @Autowired
    @Qualifier("console")
    private LoggerInterface consoleLogger;

    @Value("${custom.logToFile}")
    private boolean logToFile;
    @Value("${custom.logToConsole}")
    private boolean logToConsole;
    @Value("${custom.logMessage}")
    private boolean logMessage;
    @Value("${custom.logWarning}")
    private boolean logWarning;
    @Value("${custom.logError}")
    private boolean logError;
    @Value("${custom.logToDatabase}")
    private boolean logToDatabase;

    public void logMessage(String messageText, boolean message, boolean warning,
                                  boolean error) throws Exception {
        /* messageText.trim();
            This may cause a NullPointer Exception and it's not doing nothing with the returned value

         */

        // if (messageText == null || messageText.length() == 0) { Better use this:
        if(messageText == null || messageText.trim().isEmpty()){
            return;
        }

        // This is the correct way
        messageText = messageText.trim();


        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        /*

        This is no necessary when you use mybatis :D

        Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", dbParams.get("userName"));
		connectionProps.put("password", dbParams.get("password"));

		connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
				+ ":" + dbParams.get("portNumber") + "/", connectionProps);


        */


        // Changing integers for enums

        LogType t = LogType.DEFAULT;


        if (message && logMessage) {
            t = LogType.MESSAGE;
        }

        if (error && logError) {
            t = LogType.ERROR;
        }

        if (warning && logWarning) {
            t = LogType.WARNING;
        }


        StringBuilder l = new StringBuilder();

        // Using StringBuilder instead of String and variables, constants

        if (error && logError) {
            l.append(MessageUtil.generateMessage(LogType.ERROR, messageText));
        }

        if (warning && logWarning) {
            l.append(MessageUtil.generateMessage(LogType.WARNING, messageText));
        }

        if (message && logMessage) {
            l.append(MessageUtil.generateMessage(LogType.MESSAGE, messageText));
        }

        if(logToFile) {
            fileLogger.info(l.toString());
        }

        if(logToConsole) {
            consoleLogger.info(l.toString());
        }

        if(logToDatabase) {

            LogValue logValue = LogValue.customBuilder()
                    .message(l.toString())
                    .logType(t)
                    .build();

            logValueMapper.insert(logValue );

        }

    }

}
