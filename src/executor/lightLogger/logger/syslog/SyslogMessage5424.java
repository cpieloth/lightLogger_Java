package executor.lightLogger.logger.syslog;

import executor.lightLogger.Logger;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Syslog Message Format from RFC 5424 "The Syslog Protocol".
 *
 * @author cpieloth
 */
public class SyslogMessage5424 extends ASyslogMessage {
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
    private static final int MAX_HOSTNAME = 255;
    private static final int MAX_APP_NAME = 48;
    private static final int MAX_PROCID = 128;
    private static final int MAX_MSGID = 32;
    private static final String NIL = "-";
    private static final byte NIL_ASCII = 0x2D;
    private final String structuredData = NIL;
    private Integer version = 1;
    private String appName = NIL;
    private String procID = NIL;
    private String msgID = NIL;

    public SyslogMessage5424() {
    }

    public SyslogMessage5424(String appName, String procID) {
        this.appName = appName;
        this.procID = procID;
    }

    public static void main(String[] args) {
        SyslogMessage5424 sm = new SyslogMessage5424();
        sm.setPrival(EFacility.LOCAL4, ESeverity.NOTICE);
        sm.setDate(new Date(System.currentTimeMillis()));
        sm.setAppName("SyslogTest");
        sm.setMsg("fööösdfdf");

        try {
            System.out.println(new String(sm.getBytes(), ENCODING_UTF8));
        } catch (Exception e) {
            Logger.error(SyslogMessage5424.class, e.getMessage());
        }

    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = toASCII(hostname, MAX_HOSTNAME);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = toASCII(appName, MAX_APP_NAME);
    }

    @Override
    public String getTimestamp() {
        if (date != null)
            return TIME_FORMAT.format(date);
        else
            return NIL;
    }

    public String getProcID() {
        return procID;
    }

    public void setProcID(String procID) {
        this.procID = toASCII(procID, MAX_PROCID);
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = toASCII(msgID, MAX_MSGID);
    }

    @Override
    public byte[] getBytes() {
        StringBuilder sbHeader = new StringBuilder();
        sbHeader.append(LT).append(getPrival()).append(GT);
        sbHeader.append(version);
        sbHeader.append(SP);
        sbHeader.append(getTimestamp());
        sbHeader.append(SP);
        sbHeader.append(hostname);
        sbHeader.append(SP);
        sbHeader.append(appName);
        sbHeader.append(SP);
        sbHeader.append(procID);
        sbHeader.append(SP);
        sbHeader.append(msgID);

        byte[] headerByte;
        try {
            headerByte = sbHeader.toString().getBytes(ENCODING_ASCII);
        } catch (UnsupportedEncodingException e) {
            Logger.error(SyslogMessage5424.class, "Could not encode header: " + e.getMessage());
            return null;
        }

        byte[] sdByte;
        try {
            sdByte = (SP + structuredData).getBytes(ENCODING_ASCII);
        } catch (UnsupportedEncodingException e) {
            Logger.error(SyslogMessage5424.class, "Could not encode structured date: " + e.getMessage());
            sdByte = new byte[2];
            sdByte[0] = SP_ASCII;
            sdByte[1] = NIL_ASCII;
        }

        byte[] msgByte;
        if (msg != null && !"".equals(msg)) {
            final byte[] tmp;
            try {
                tmp = msg.getBytes(ENCODING_UTF8);
                msgByte = new byte[1 + tmp.length];
                msgByte[0] = SP_ASCII;
                System.arraycopy(tmp, 0, msgByte, 1, tmp.length);
            } catch (UnsupportedEncodingException e) {
                Logger.error(SyslogMessageBSD.class, "Could not encode msg: " + e.getMessage());
                msgByte = new byte[0];
            }
        } else {
            msgByte = new byte[0];
        }

        byte[] syslogMessage = new byte[headerByte.length + sdByte.length + msgByte.length];
        System.arraycopy(headerByte, 0, syslogMessage, 0, headerByte.length);
        System.arraycopy(sdByte, 0, syslogMessage, headerByte.length, sdByte.length);
        System.arraycopy(msgByte, 0, syslogMessage, headerByte.length + sdByte.length, msgByte.length);
        return syslogMessage;
    }
}
