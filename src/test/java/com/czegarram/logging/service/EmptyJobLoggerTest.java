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
@ActiveProfiles("test2")
public class EmptyJobLoggerTest {

    @Autowired
    private JobLogger jobLogger;

    @Test(expected = Exception.class)
    public void oneFlagAndString() throws  Exception{
        jobLogger.logMessage("Test", true, false, false);
    }
}
