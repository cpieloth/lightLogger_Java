package executor.lightLogger;

import executor.lightLogger.formatter.IFormatter;
import executor.lightLogger.level.ILevel;
import executor.lightLogger.logger.ConsoleFactory;
import executor.lightLogger.logger.ILogger;

import java.util.Properties;
import java.util.Set;

/**
 * Loads and configures a ILogger implementation.
 *
 * @author cpieloth
 */
public abstract class ALoggerFactory {

    protected static int mask = Integer.MAX_VALUE;
    protected static Class<? extends ALoggerFactory> factoryClass = ConsoleFactory.class;
    protected static IFormatter formatter = null;
    private static ALoggerFactory instance = new ConsoleFactory();

    protected ALoggerFactory() {
    }

    public static synchronized ALoggerFactory getInstance(Class<? extends ALoggerFactory> clazz, Properties properties) {
        if (instance == null || (clazz != factoryClass)) {
            factoryClass = clazz;
            try {
                instance = ALoggerFactory.class.cast(clazz.newInstance());
            } catch (Exception e) {
                instance = new ConsoleFactory();
                Logger.error(ALoggerFactory.class, "Could not create factory instance! Using "
                        + instance.getClass().getSimpleName() + " instead.");
            }
            instance.loadProperties(properties);
        }
        return instance;
    }

    protected static Class<? extends ALoggerFactory> getFactoryClass() {
        return factoryClass;
    }

    /**
     * @return The log mask for log level selection.
     */
    public static int getLogMask() {
        return mask;
    }

    public static void setLogMask(ILevel level) {
        setLogMask(level.getValue());
    }

    /**
     * Sets the log mask for log level selection.
     *
     * @param value Log mask as value.
     */
    public static void setLogMask(int value) {
        mask = value;
    }

    public static void setLogMask(Set<ILevel> level) {
        mask = 0;
        int tmp;
        for (ILevel lvl : level) {
            tmp = mask;
            mask += lvl.getValue();
            if (mask < tmp) {
                mask = tmp;
                return;
            }
        }
    }

    public static IFormatter getFormatter() {
        return formatter;
    }

    public static void setFormatter(IFormatter formatter) {
        ALoggerFactory.formatter = formatter;
    }

    /**
     * Initializes the logger with settings defined by the implementation.
     *
     * @param properties
     *            Ready to read properties instance,
     * @return true if all properties are loaded.
     */
    public abstract boolean loadProperties(Properties properties);

    public abstract ILogger getLogger(String name);

    public ILogger getLogger(Class<?> name) {
        if (name != null)
            return getLogger(name.getName());
        else
            return getLogger(ILogger.UNKNOWN_NAME);
    }

    public ILogger getLogger(Object name) {
        if (name != null)
            return getLogger(name.getClass());
        else
            return getLogger(ILogger.UNKNOWN_NAME);
    }

}
