package executor.lightLogger.logger.syslog;

public enum ESeverity {
    EMERGENCY((short) 0, "Emergency: system is unusable"),
    ALERT((short) 1, "Alert: action must be taken immediately"),
    CRITICAL((short) 2, "Critical: critical conditions"),
    ERROR((short) 3, "Error: error conditions"),
    WARNING((short) 4, "Warning: warning conditions"),
    NOTICE((short) 5, "Notice: normal but significant condition"),
    INFO((short) 6, "Informational: informational messages"),
    DEBUG((short) 7, "Debug: debug-level messages");

    public final short code;
    public final String severity;

    ESeverity(short code, String severity) {
        this.code = code;
        this.severity = severity;
    }
}
