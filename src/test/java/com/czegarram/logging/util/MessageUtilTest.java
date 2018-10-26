package com.czegarram.logging.util;

import com.czegarram.logging.dto.LogType;
import org.junit.Test;

import org.junit.Assert;

public class MessageUtilTest {

    @Test(expected = Exception.class)
    public void nullParameters() throws Exception{
        MessageUtil.generateMessage(null, null);
    }

    @Test(expected = Exception.class)
    public void nullSeverity() throws Exception{
        MessageUtil.generateMessage(null, "Test");
    }

    @Test(expected = Exception.class)
    public void nullMessage() throws Exception{
        MessageUtil.generateMessage(LogType.MESSAGE, null);
    }

    @Test
    public void message() throws Exception{
        String messageText = "TEST1";
        String message = MessageUtil.generateMessage(LogType.MESSAGE, messageText);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.startsWith(LogType.MESSAGE.toString()));
        Assert.assertTrue(message.contains(messageText));
    }

    @Test
    public void error() throws Exception{
        String messageText = "TEST2";
        String message = MessageUtil.generateMessage(LogType.ERROR, messageText);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.startsWith(LogType.ERROR.toString()));
        Assert.assertTrue(message.contains(messageText));
    }

    @Test
    public void warning() throws Exception{
        String messageText = "TEST3";
        String message = MessageUtil.generateMessage(LogType.WARNING, messageText);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.startsWith(LogType.WARNING.toString()));
        Assert.assertTrue(message.contains(messageText));
    }

}
