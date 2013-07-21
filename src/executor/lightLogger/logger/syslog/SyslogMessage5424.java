package executor.lightLogger.logger.syslog;

import executor.lightLogger.Logger;

import java.util.Date;

/**
 * Basic data structure for Syslog message. (RFC 5424)
 */
public class SyslogMessage {
    private final String structuredData = SyslogConstants.NIL;
    private short prival = 0;
    private Integer version = 1;
    private String hostname = SyslogConstants.NIL;
    private Date timeStamp = null;
    private String appName = SyslogConstants.NIL;
    private String procID = SyslogConstants.NIL;
    private String msgID = SyslogConstants.NIL;
    private String msg = null;

    private static String toASCII(String s, int length) {
        if (s.length() > length) {
            Logger.error(SyslogMessage.class, "String to long!");
            return s.substring(0, length).replaceAll("[^\\x21-\\x7E]", "");
        } else {
            return s.replaceAll("[^\\x21-\\x7E]", "");
        }
    }

    public short getPrival() {
        return prival;
    }

    public void setPrival(short prival) {
        if (SyslogConstants.MIN_PRIVAL <= prival && prival <= SyslogConstants.MAX_PRIVAL)
            this.prival = prival;
        else
            Logger.error(SyslogMessage.class, "prival not in range!");
    }

    public void setPrival(EFacility facility, ESeverity severity) {
        this.prival = (short) (facility.code * 8 + severity.code);
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostName(String hostname) {
        this.hostname = toASCII(hostname, SyslogConstants.MAX_HOSTNAME);
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = toASCII(appName, SyslogConstants.MAX_APP_NAME);
    }

    public String getProcID() {
        return procID;
    }

    public void setProcID(String procID) {
        this.procID = toASCII(procID, SyslogConstants.MAX_PROCID);
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = toASCII(msgID, SyslogConstants.MAX_MSGID);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public byte[] buildSyslogMessage() throws Exception {
        StringBuilder sbHeader = new StringBuilder();
        sbHeader.append(SyslogConstants.LT).append(prival).append(SyslogConstants.GT);
        sbHeader.append(version);
        sbHeader.append(SyslogConstants.SP);
        if (timeStamp != null)
            sbHeader.append(SyslogConstants.TIME_FORMAT.format(timeStamp));
        else
            sbHeader.append(SyslogConstants.NIL);
        sbHeader.append(SyslogConstants.SP);
        sbHeader.append(hostname);
        sbHeader.append(SyslogConstants.SP);
        sbHeader.append(appName);
        sbHeader.append(SyslogConstants.SP);
        sbHeader.append(procID);
        sbHeader.append(SyslogConstants.SP);
        sbHeader.append(msgID);

        byte[] headerByte;
        headerByte = sbHeader.toString().getBytes(SyslogConstants.ENCODING_ASCII);

        byte[] sdByte;
        sdByte =(SyslogConstants.SP + structuredData).getBytes(SyslogConstants.ENCODING_ASCII);

        byte[] msgByte;
        if (msg != null && !"".equals(msg)) {
            final byte[] tmp = msg.getBytes(SyslogConstants.ENCODING_UTF8);
            msgByte = new byte[1 + tmp.length];
            msgByte[0] = SyslogConstants.SP_ASCII;
            System.arraycopy(tmp, 0, msgByte, 1, tmp.length);
        } else {
            msgByte = new byte[0];
        }

        byte[] syslogMessage = new byte[headerByte.length + sdByte.length + msgByte.length];
        System.arraycopy(headerByte, 0, syslogMessage, 0, headerByte.length);
        System.arraycopy(sdByte, 0, syslogMessage, headerByte.length, sdByte.length);
        System.arraycopy(msgByte, 0, syslogMessage, headerByte.length + sdByte.length, msgByte.length);
        return syslogMessage;
    }

    public static void main(String[] args) {
        SyslogMessage sm = new SyslogMessage();
        sm.setPrival(EFacility.LOCAL4, ESeverity.NOTICE);
        sm.setTimeStamp(new Date(System.currentTimeMillis()));
        sm.setAppName("SyslogTest");
        sm.setMsg("fööösdfdf");

        try {
            System.out.println(new String(sm.buildSyslogMessage(), SyslogConstants.ENCODING_UTF8));
        } catch (Exception e) {
            Logger.error(SyslogMessage.class, e.getMessage());
        }

    }
}
