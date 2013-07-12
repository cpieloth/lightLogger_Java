package executor.lightLogger.logger.syslog;

import executor.lightLogger.Logger;

import java.net.*;

/**
 * Sends a Syslog Message to a collector or relay.
 */
public class SyslogSender {

    private InetAddress address;
    private int port;

    private DatagramSocket socket = null;

    private static SyslogSender instance = null;

    public static synchronized SyslogSender getInstance(String address, int port) {
        if(instance== null)
            // TODO(pieloth)
            instance = new SyslogSender(address, port);
        return instance;
    }

    private SyslogSender(String address, int port) throws UnknownHostException {
        this.address = InetAddress.getByName(address);
        this.port = port;
        connect();
    }

    public boolean connect() {
        if (socket == null) {
            try {
                socket = new DatagramSocket();
                socket.connect(address, port);
            } catch (SocketException e) {
                Logger.error(SyslogSender.class, e.getMessage());
                return false;
            }
        }
        if (socket.isConnected())
            return true;
        else {
            Logger.error(SyslogSender.class, "Could not connect!");
            return false;
        }

    }

    public void disconnect() {
        if (socket == null)
            return;
        socket.disconnect();
        socket = null;
        return;
    }

    public boolean sendMessage(SyslogMessage syslogMsg) {
        if (socket == null || !socket.isConnected()) {
            if (!connect()) {
                Logger.error(SyslogSender.class, "Could not send message!");
                return false;
            }
        }

        try {
            final byte[] data = syslogMsg.buildSyslogMessage();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.send(packet);
        } catch (Exception e) {
            Logger.error(SyslogSender.class, e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("fff");
        try {
            SyslogSender syslogSender = new SyslogSender(null, 666);
            SyslogMessage sm = new SyslogMessage();
            syslogSender.sendMessage(sm);
        } catch (UnknownHostException e) {
            Logger.error(SyslogSender.class, e.getMessage());
        }
    }


}
