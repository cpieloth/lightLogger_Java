package executor.lightLogger.logger.syslog;

/**
 * Interface for syslog transport sender. Sends a syslog message to a collector or receiver.
 *
 * @author cpoieloth
 */
public interface ISyslogSender {
    public boolean connect();

    public boolean disconnect();

    public boolean sendMessage(ISyslogMessage syslog);
}
