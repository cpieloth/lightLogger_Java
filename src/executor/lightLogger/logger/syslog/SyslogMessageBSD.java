package executor.lightLogger.logger.syslog;

import executor.lightLogger.Logger;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Syslog Packet from RFC 3164 "The BSD syslog Protocol".
 *
 * @author cpieloth
 */
public class SyslogMessageBSD extends ASyslogMessage {

    private static final SimpleDateFormat TIME_FORMAT1 = new SimpleDateFormat("MMM dd HH:mm:ss");
    private static final SimpleDateFormat TIME_FORMAT2 = new SimpleDateFormat("MMM  d HH:mm:ss");
    private static final int MAX_HOSTNAME = 39;
    private static final int MAX_TAG = 32;
    private String tag = "";

    public SyslogMessageBSD() {
        date = new Date(0);
    }

    public SyslogMessageBSD(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = toASCII(tag, MAX_TAG);
    }

    @Override
    public String getTimestamp() {
        String timestamp = TIME_FORMAT1.format(date);
        if (timestamp.charAt(4) == '0') {
            timestamp = TIME_FORMAT2.format(date);
        }
        return timestamp;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = toASCII(hostname, MAX_HOSTNAME);
    }

    @Override
    public byte[] getBytes() {
        StringBuilder sbHeader = new StringBuilder();
        sbHeader.append(LT).append(getPrival()).append(GT);
        sbHeader.append(getTimestamp());
        sbHeader.append(SP);
        sbHeader.append(hostname);
        sbHeader.append(SP);
        if (tag != null && !"".equals(tag)) {
            sbHeader.append(tag);
            sbHeader.append(SP);
        }

        byte[] headerByte;
        try {
            headerByte = sbHeader.toString().getBytes(ENCODING_ASCII);
        } catch (UnsupportedEncodingException e) {
            Logger.error(SyslogMessageBSD.class, "Could not encode header: " + e.getMessage());
            return null;
        }

        byte[] msgByte;
        if (msg != null && !"".equals(msg)) {
            try {
                msgByte = msg.getBytes(ENCODING_UTF8);
            } catch (UnsupportedEncodingException e) {
                Logger.error(SyslogMessageBSD.class, "Could not encode msg: " + e.getMessage());
                msgByte = new byte[0];
            }
        } else {
            msgByte = new byte[0];
        }

        byte[] syslogMessage = new byte[headerByte.length + msgByte.length];
        System.arraycopy(headerByte, 0, syslogMessage, 0, headerByte.length);
        System.arraycopy(msgByte, 0, syslogMessage, headerByte.length, msgByte.length);
        return syslogMessage;
    }
}