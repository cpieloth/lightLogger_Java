package executor.lightLogger.logger;

import executor.lightLogger.ALoggerFactory;
import executor.lightLogger.Logger;
import executor.lightLogger.formatter.BasicFormatter;
import executor.lightLogger.formatter.IFormatter;

import java.util.Properties;

/**
 * TODO
 */
public class FileFactory extends ALoggerFactory {

    private String file = Config.FILENAME.defValue;

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
            Logger.error(FileFactory.class,
                    "Could instanciate formatter!");
            success = false;
        }

        val = properties.getProperty(Config.FILENAME.key,
                Config.FILENAME.defValue);
        file = val;

        return success;
    }

    @Override
    public ILogger getLogger(String name) {
        FileLogger logger = new FileLogger(name);
        logger.setFormatter(formatter);
        logger.setLogMask(mask);
        logger.setFile(file);
        return logger;
    }

    public static enum Config {
        FORMATTER(FileFactory.class.getSimpleName() + ".formatter",
                BasicFormatter.class.getName()),
        FILENAME(FileFactory.class.getSimpleName() + ".file", FileLogger.class
                .getSimpleName() + ".log");
        public final String key;
        public final String defValue;

        Config(String key, String val) {
            this.key = key;
            this.defValue = val;
        }
    }
}
