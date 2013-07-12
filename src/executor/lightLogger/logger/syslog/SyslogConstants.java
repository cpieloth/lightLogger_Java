package executor.lightLogger.logger.syslog;

import java.text.SimpleDateFormat;

/**
 * Constants for syslog protocol.
 */
public interface SyslogConstants {

    public static final String ENCODING_ASCII = "US-ASCII";

    public static final String ENCODING_UTF8 = "UTF8";

    public static final char SP = 32;

    public static final byte SP_ASCII = 0x20;

    public static final String NIL = "-";

    public static final char LT = 60;

    public static final char GT = 62;

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");

    public static final short MIN_PRIVAL = 0;
    public static final short MAX_PRIVAL = 191;
    public static final int MAX_HOSTNAME = 255;
    public static final int MAX_APP_NAME = 48;
    public static final int MAX_PROCID = 128;
    public static final int MAX_MSGID = 32;
}
