package executor.lightLogger;

import executor.lightLogger.formatter.IFormatter;
import executor.lightLogger.level.ILevel;
import executor.lightLogger.logger.ConsoleFactory;
import executor.lightLogger.logger.ConsoleLogger;
import executor.lightLogger.logger.ILogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Central class for simple logging.
 *
 * @author executor
 */

public class Logger {
    public static final String PROPERTIES_FILE = "lightLogger.properties";
    private static Properties properties = null;
    private static boolean isInit = false;
    private static ALoggerFactory factory = new ConsoleFactory();

    public static ILogger getInstance(Class<?> name) {
        if (name != null)
            return getInstance(name.getName());
        else
            return getInstance(ILogger.UNKNOWN_NAME);
    }

    public static ILogger getInstance(Object name) {
        if (name != null)
            return getInstance(name.getClass());
        else
            return getInstance(ILogger.UNKNOWN_NAME);
    }

    public static ILogger getInstance(String name) {
        initialize();

        return factory.getLogger(name);
    }

    private static void initialize() {
        if (isInit)
            return;
        readProperties(PROPERTIES_FILE);
        loadProperties(properties);
        isInit = true;
    }

    @SuppressWarnings("unchecked")
    private static void loadProperties(Properties properties) {
        if (properties == null)
            return;

        boolean err = false;
        String val;
        try {
            val = properties.getProperty(Config.FACTORY.key,
                    Config.FACTORY.defValue);
            final Class<? extends ALoggerFactory> clazz = (Class<? extends ALoggerFactory>) Class.forName(val);
            factory = ALoggerFactory.getInstance(clazz, properties);
        } catch (Exception e) {
            err = true;
        }

        Integer mask = Integer.MAX_VALUE;
        try {
            val = properties.getProperty(Config.LOG_MASK.key,
                    Config.LOG_MASK.defValue);
            mask = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            err = true;
        }
        ALoggerFactory.setLogMask(mask);

        if (err)
            error(Logger.class,
                    "Could not load all properties! Using defaults instead.");
    }

    private static void readProperties(String file) {
        if (properties != null)
            return;

        properties = new Properties();
        InputStream stream = null;
        try {
            stream = Logger.class.getResourceAsStream(file);
            properties.load(stream);
        } catch (Exception e) {
            error(Logger.class, "Could not load " + file + "!");
        } finally {
            if (stream != null)
                try {
                    stream.close();
                } catch (IOException e) {
                    error(Logger.class, "Could not close properties file!");
                }
        }
    }

    public static void error(Class<?> clazz, String message) {
        System.err.println(clazz.getSimpleName() + ": " + message);
    }

    /**
     * @return The log mask for log level selection.
     */
    public static int getLogMask() {
        return ALoggerFactory.getLogMask();
    }

    /**
     * Sets the log mask for log level selection.
     *
     * @param value Log mask as value.
     */
    public static void setLogMask(int value) {
        ALoggerFactory.setLogMask(value);
    }

    /**
     * Sets the log mask for log level selection by summation of each level's
     * value.
     *
     * @param level Set of levels to log
     */
    public static void setLogMask(Set<ILevel> level) {
        ALoggerFactory.setLogMask(level);
    }

    public static void setLogMask(ILevel level) {
        ALoggerFactory.setLogMask(level.getValue());
    }

    public static Class<? extends ALoggerFactory> getFactoryClass() {
        return factory.getFactoryClass();
    }

    public static void setFactoryClass(Class<? extends ALoggerFactory> clazz) {
        factory = ALoggerFactory.getInstance(clazz, properties);
    }

    public static IFormatter getFormatter() {
        return ALoggerFactory.getFormatter();
    }

    public static void setFormatter(IFormatter formatter) {
        ALoggerFactory.setFormatter(formatter);
    }

    public static enum Config {
        FACTORY(Logger.class.getSimpleName() + ".factory",
                ConsoleLogger.class.getName()),
        LOG_MASK(Logger.class.getSimpleName() + ".mask",
                String.valueOf(Integer.MAX_VALUE));

        public final String key;
        public final String defValue;

        Config(String key, String val) {
            this.key = key;
            this.defValue = val;
        }
    }

}
