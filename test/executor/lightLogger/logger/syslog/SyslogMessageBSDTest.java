package executor.lightLogger.logger.syslog;

import executor.lightLogger.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SyslogMessageBSDTest {
    @Test
    public void testGetTimestamp() throws Exception {
        // Testing days less than 10. Must have a space instead a zero.
        SyslogMessageBSD sm = new SyslogMessageBSD();

        sm.setDate(new Date(0));
        final String actual1 = sm.getTimestamp();
        final String expected1 = "Jan  1 01:00:00";
        Assert.assertEquals(expected1, actual1);

        sm.setDate(new Date(10*24*60*60*1000));
        final String actual2 = sm.getTimestamp();
        final String expected2 = "Jan 11 01:00:00";
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void testGetBytes() throws Exception {
        SyslogMessageBSD sm = new SyslogMessageBSD();
        sm.setPrival(EFacility.LOCAL4, ESeverity.NOTICE);
        sm.setDate(new Date(10*24*60*60*1000));
        sm.setHostname("node1");
        sm.setTag("SyslogTest");
        sm.setMsg("Coffee break!");

        final String actual = new String(sm.getBytes(), SyslogMessageBSD.ENCODING_UTF8);
        final String expected = "<165>Jan 11 01:00:00 node1 SyslogTest Coffee break!";

        Assert.assertEquals(expected, actual);
    }
}
