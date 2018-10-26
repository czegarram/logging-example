package com.czegarram.logging.service;

import com.czegarram.logging.config.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
@ActiveProfiles("test")
public class JobLoggerTest {

    @Autowired
    private JobLogger jobLogger;

    @Test
    public void nullString() throws  Exception{
        jobLogger.logMessage(null, true, false, false);
    }

    @Test
    public void emptyString() throws  Exception{
        jobLogger.logMessage("", false, true, false);
    }

    @Test
    public void emptyString2() throws  Exception{
        jobLogger.logMessage("    ", false, false, true);
    }

    @Test(expected = Exception.class)
    public void noneOfFlagsTrue() throws  Exception{
        jobLogger.logMessage("Test", false, false, false);
    }

    @Test
    public void oneFlagAndString() throws  Exception{
        jobLogger.logMessage("Test", true, false, false);
    }

}
