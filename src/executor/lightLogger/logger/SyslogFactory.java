package executor.lightLogger.logger;

import executor.lightLogger.ALoggerFactory;
import executor.lightLogger.Logger;
import executor.lightLogger.logger.syslog.EFacility;
import executor.lightLogger.logger.syslog.ISyslogSender;
import executor.lightLogger.logger.syslog.SyslogSenderUDP;

import java.util.Properties;

/**
 * Creates a logger for a syslog relay or collector.
 *
 * @author cpieloth
 */
public class SyslogFactory extends ALoggerFactory {

    private ISyslogSender sender;
    private EFacility facility = EFacility.LOCAL7;
    private String hostname = "localhost";
    private String tag = "";
    private String address = "127.0.0.1";
    private int port = 514;

    @Override
    public boolean loadProperties(Properties properties) {
        if (properties == null) {
            Logger.error(SyslogFactory.class, "No properties!");
            return false;
        }

        boolean success = false;
        String val;

        address = properties.getProperty(Config.ADDRESS.key,
                Config.ADDRESS.defValue);

        val = properties.getProperty(Config.PORT.key,
                Config.PORT.defValue);
        port = Integer.parseInt(val);

        val = properties.getProperty(Config.FACILITY.key,
                Config.FACILITY.defValue);
        for (EFacility f : EFacility.values()) {
            if (String.valueOf(f.code).equals(val)) {
                facility = f;
                success = true;
                break;
            }
        }

        hostname = properties.getProperty(Config.HOSTNAME.key,
                Config.HOSTNAME.defValue);

        tag = properties.getProperty(Config.TAG.key,
                Config.TAG.defValue);

        sender = SyslogSenderUDP.getInstance(address, port);
        if(sender == null || !sender.connect()) {
            Logger.error(SyslogFactory.class, "Error in Syslog Sender!");
            success = false;
        }

        return success;
    }

    @Override
    public ILogger getLogger(String name) {
        SyslogLogger log = new SyslogLogger();
        log.setName(name);
        log.setLogMask(mask);
        log.setSender(sender);
        log.setFacility(facility);
        log.setHostname(hostname);
        log.setTag(tag);
        return log;
    }

    public static enum Config {
        ADDRESS(SyslogFactory.class.getSimpleName() + ".address",
                "127.0.0.1"),
        PORT(SyslogFactory.class.getSimpleName() + ".port", "514"),
        FACILITY(SyslogFactory.class.getSimpleName() + ".facility", "23"),
        TAG(SyslogFactory.class.getSimpleName() + ".tag", ""),
        HOSTNAME(SyslogFactory.class.getSimpleName() + ".hostname", "localhost");

        public final String key;
        public final String defValue;

        Config(String key, String val) {
            this.key = key;
            this.defValue = val;
        }
    }
}
