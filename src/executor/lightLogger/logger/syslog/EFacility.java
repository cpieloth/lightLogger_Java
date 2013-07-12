package executor.lightLogger.logger.syslog;

public enum EFacility {
    KERNEL((short) 0, "kernel messages"),
    USER((short) 1, "user-level messages"),
    MAIL((short) 2, "mail system"),
    SYSTEM((short) 3, "system daemons"),
    SECURITY1((short) 4, "security/authorization messages"),
    SYSLOGD((short) 5, "message generated internally by syslogd"),
    PRINTER((short) 6, "line printer subsystem"),
    NETWORK((short) 7, "network news system"),
    UUCP((short) 8, "UUCP subsystem"),
    CLOCK1((short) 9, "clock daemon"),
    SECURITY2((short) 10, "security/authorization messages"),
    FTP((short) 11, "FTP daemon"),
    NTP((short) 12, "NTP subsystem"),
    LOG_AUDIT((short) 13, "log audit"),
    LOG_ALERT((short) 14, "log alert"),
    CLOCK2((short) 15, "clock daemon"),
    LOCAL0((short) 16, "local use 0"),
    LOCAL1((short) 17, "local use 1"),
    LOCAL2((short) 18, "local use 2"),
    LOCAL3((short) 19, "local use 3"),
    LOCAL4((short) 20, "local use 4"),
    LOCAL5((short) 21, "local use 5"),
    LOCAL6((short) 22, "local use 6"),
    LOCAL7((short) 23, "local use 7");

    public final short code;
    public final String facility;

    EFacility(short code, String facility) {
        this.code = code;
        this.facility = facility;
    }
}
