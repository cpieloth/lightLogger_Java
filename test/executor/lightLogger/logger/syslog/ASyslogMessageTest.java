package executor.lightLogger.logger.syslog;

import junit.framework.Assert;
import org.junit.Test;

public class ASyslogMessageTest {
    @Test
    public void testGetPrival() throws Exception {
        SyslogMessageBSD bsd = new SyslogMessageBSD();
        bsd.setPrival(EFacility.LOCAL4, ESeverity.NOTICE);
        final short actual1 = bsd.getPrival();
        final short expected1 = (short)165;
        Assert.assertEquals(expected1, actual1);

        SyslogMessage5424 syslog = new SyslogMessage5424();
        syslog.setPrival(EFacility.KERNEL, ESeverity.EMERGENCY);
        final short actual2 = syslog.getPrival();
        final short expected2 = (short)0;
        Assert.assertEquals(expected2, actual2);
    }
}
