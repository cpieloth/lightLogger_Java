package executor.lightLogger.logger;

import executor.lightLogger.ALoggerFactory;
import executor.lightLogger.Logger;
import executor.lightLogger.formatter.BasicFormatter;
import executor.lightLogger.formatter.IFormatter;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: christof
 * Date: 23.07.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleFactory extends ALoggerFactory {

    public static enum Config {
        FORMATTER(ConsoleLogger.class.getSimpleName() + ".formatter",
                BasicFormatter.class.getName());

        public final String key;
        public final String defValue;

        Config(String key, String val) {
            this.key = key;
            this.defValue = val;
        }
    }

    public ConsoleFactory() {
        formatter = new BasicFormatter();
    }

    @Override
    public boolean loadProperties(Properties properties) {
        if (properties == null) {
            return false;
        }

        boolean success = true;

        String val;
        try {
            val = properties.getProperty(Config.FORMATTER.key,
                    Config.FORMATTER.defValue);
            formatter = ((Class<? extends IFormatter>) Class.forName(val))
                    .newInstance();
        } catch (Exception e) {
            Logger.error(ALoggerFormatable.class,
                    "Could instanciate formatter!");
            success = false;
        }

        return success;
    }

    @Override
    public ILogger getLogger(String name) {
        ConsoleLogger logger = new ConsoleLogger(name);
        logger.setFormatter(formatter);
        logger.setLogMask(mask);
        return logger;
    }
}
