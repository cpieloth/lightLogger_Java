package executor.lightLogger.logger;

import executor.lightLogger.Logger;
import executor.lightLogger.level.Default;
import executor.lightLogger.level.ILevel;
import executor.lightLogger.logger.syslog.*;

import java.util.*;

/**
 * Logs to a syslog relay or collector.
 *
 * @author cpieloth
 */
public class SyslogLogger extends ALogger {

    private static final Map<Default, ESeverity> LEVEL_TO_SERVERITY;

    static {
        EnumMap<Default, ESeverity> tmp = new EnumMap(Default.class);
        tmp.put(Default.TRACE, ESeverity.DEBUG);
        tmp.put(Default.DEBUG, ESeverity.DEBUG);
        tmp.put(Default.INFO, ESeverity.INFO);
        tmp.put(Default.WARN, ESeverity.WARNING);
        tmp.put(Default.ERROR, ESeverity.ERROR);
        tmp.put(Default.FATAL, ESeverity.ALERT);
        LEVEL_TO_SERVERITY = Collections.unmodifiableMap(tmp);
    }

    private ISyslogSender sender;

    private String hostname;

    private String tag;

    private EFacility facility;

    public SyslogLogger() {
        this(ILogger.UNKNOWN_NAME);
    }

    public SyslogLogger(String name) {
        super(name);
    }

    @Override
    public void log(ILevel level, Object message) {
        log(level, message, null);
    }

    @Override
    public void log(ILevel level, Object message, Throwable throwable) {
        if (!evaluate(level)) {
            return;
        }

        ESeverity severity = LEVEL_TO_SERVERITY.get(level);
        if(severity == null) {
            Logger.error(SyslogLogger.class, "Unknown level: " + level.getValue());
            severity = ESeverity.NOTICE;
        }

        ISyslogMessage syslog = new SyslogMessageBSD(tag);
        syslog.setPrival(facility, severity);
        syslog.setHostname(hostname);
        syslog.setDate(new Date(System.currentTimeMillis()));
        if (throwable == null)
            syslog.setMsg(name + ": " + message.toString());
        else
            syslog.setMsg(name + ": " + message.toString() + "\n" + throwable.getMessage());

        if (!sender.sendMessage(syslog)) {
            Logger.error(SyslogLogger.class, "Message was not logged!");
        }
    }

    @Override
    public void fatal(Object message) {
        log(Default.FATAL, message);
    }

    @Override
    public void fatal(Object message, Throwable throwable) {
        log(Default.FATAL, message, throwable);
    }

    @Override
    public void error(Object message) {
        log(Default.ERROR, message);
    }

    @Override
    public void error(Object message, Throwable throwable) {
        log(Default.ERROR, message, throwable);
    }

    @Override
    public void warn(Object message) {
        log(Default.WARN, message);
    }

    @Override
    public void warn(Object message, Throwable throwable) {
        log(Default.WARN, message, throwable);
    }

    @Override
    public void info(Object message) {
        log(Default.INFO, message);
    }

    @Override
    public void debug(Object message) {
        log(Default.DEBUG, message);
    }

    @Override
    public void trace(Object message) {
        log(Default.TRACE, message);
    }

    public void setSender(ISyslogSender sender) {
        this.sender = sender;
    }

    public void setFacility(EFacility facility) {
        this.facility = facility;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
