package executor.lightLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import executor.lightLogger.level.ILevel;
import executor.lightLogger.logger.ConsoleLogger;
import executor.lightLogger.logger.ILogger;

/**
 * Central class for simple logging.
 * 
 * @author executor
 * 
 */

public class Logger {

	public static enum Config {
		IMPLEMENTATION(Logger.class.getSimpleName() + ".implementation",
				ConsoleLogger.class.getName()), LOG_MASK(Logger.class
				.getSimpleName() + ".mask", String.valueOf(Integer.MAX_VALUE));

		public final String key;
		public final String defValue;

		Config(String key, String val) {
			this.key = key;
			this.defValue = val;
		}
	}

	public static final String PROPERTIES_FILE = "lightLogger.properties";

	private static Properties properties = null;
	private static boolean isInit = false;

	private static Class<? extends ILogger> loggerClass = ConsoleLogger.class;
	private static int mask = Integer.MAX_VALUE;

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

		ILogger logger = null;
		try {
			logger = ILogger.class.cast(loggerClass.newInstance());
		} catch (Exception e) {
			logger = new ConsoleLogger();
			error(Logger.class, "Could not create logger instance! Using "
					+ logger.getClass().getSimpleName() + " instead.");
		}

		logger.setName(name);
		logger.setLogMask(mask);
		logger.loadProperties(properties);

		return logger;
	}

	public static void initialize() {
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
			val = properties.getProperty(Config.IMPLEMENTATION.key,
					Config.IMPLEMENTATION.defValue);
			loggerClass = (Class<? extends ILogger>) Class.forName(val);

			val = properties.getProperty(Config.LOG_MASK.key,
					Config.LOG_MASK.defValue);
			mask = Integer.parseInt(val);
		} catch (Exception e) {
			err = true;
		}

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
	 * 
	 * @return The log mask for log level selection.
	 */
	public static int getLogMask() {
		return mask;
	}

	/**
	 * Sets the log mask for log level selection by summation of each level's
	 * value.
	 * 
	 * @param level
	 *            Set of levels to log
	 */
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
	
	public static void setLogMask(ILevel level) {
		setLogMask(level.getValue());
	}

	/**
	 * Sets the log mask for log level selection.
	 * 
	 * @param value
	 *            Log mask as value.
	 */
	public static void setLogMask(int value) {
		mask = value;
	}
	
	public static Class<? extends ILogger> getLoggerClass() {
		return loggerClass;
	}
	
	public static void setLoggerClass(Class<? extends ILogger> clazz) {
		loggerClass = clazz;
	}

}
