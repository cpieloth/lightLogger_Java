package executor.lightLogger.logger.syslog;

import java.util.Date;

/**
 * Common parts of a syslog message (RFC3164, RFC5424)
 *
 * @author cpieloth
 */
public interface ISyslogMessage {
    public short getPrival();

    public void setPrival(EFacility facility, ESeverity severity);

    public EFacility getFacility();

    public void setFacility(EFacility facility);

    public ESeverity getSeverity();

    public void settSeverity(ESeverity severity);

    public Date getDate();

    public void setDate(Date date);

    public String getTimestamp();

    public String getHostname();

    public void setHostname(String hostname);

    public String getMsg();

    public void setMsg(String msg);

    public byte[] getBytes();
}
