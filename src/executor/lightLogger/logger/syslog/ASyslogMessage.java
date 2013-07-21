package executor.lightLogger.logger.syslog;

import executor.lightLogger.Logger;

import java.util.Date;

/**
 * Basics of a syslog message.
 *
 * @author cpieloth
 */
public abstract class ASyslogMessage implements ISyslogMessage {
    protected static final char SP = 32;

    protected static final byte SP_ASCII = 0x20;

    protected static final char LT = 60;

    protected static final char GT = 62;

    protected static final String ENCODING_ASCII = "US-ASCII";

    protected static final String ENCODING_UTF8 = "UTF8";

    protected EFacility facility = EFacility.KERNEL;
    protected ESeverity severity = ESeverity.EMERGENCY;
    protected Date date = null;
    protected String hostname = null;
    protected String msg = null;

    protected static String toASCII(String s, int length) {
        if (s.length() > length) {
            Logger.error(ASyslogMessage.class, "String to long!");
            return s.substring(0, length).replaceAll("[^\\x21-\\x7E]", "");
        } else {
            return s.replaceAll("[^\\x21-\\x7E]", "");
        }
    }

    @Override
    public short getPrival() {
        return (short) (facility.code * 8 + severity.code);
    }

    @Override
    public void setPrival(EFacility facility, ESeverity severity) {
        this.facility = facility;
        this.severity = severity;
    }

    @Override
    public EFacility getFacility() {
        return facility;
    }

    @Override
    public void setFacility(EFacility facility) {
        this.facility = facility;
    }

    @Override
    public ESeverity getSeverity() {
        return severity;
    }

    @Override
    public void settSeverity(ESeverity severity) {
        this.severity = severity;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
