package executor.lightLogger;

import java.util.Set;

/**
 * Central class for simple logging.
 * 
 * @author executor
 * 
 */

public class Logger {

	private static ILogger logger = new BasicLogger();

	/**
	 * 
	 * @return The log mask for log level selection.
	 */
	public static int getLogMask() {
		return Logger.logger.getLogMask();
	}

	/**
	 * Sets the log mask for log level selection by summation of each level's
	 * value.
	 * 
	 * @param level
	 *            Set of levels to log
	 */
	public static void setLogMask(Set<Level> level) {
		Logger.logger.setLogMask(level);
	}

	/**
	 * Sets the log mask for log level selection.
	 * 
	 * @param value
	 *            Log mask as value.
	 */
	public static void setLogMask(int value) {
		Logger.logger.setLogMask(value);
	}

	/**
	 * 
	 * @return Used implementation of ILogger.
	 */
	public static IClassLogger getLogger() {
		return logger;
	}

	/**
	 * Sets implementation of ILogger.
	 * 
	 * @param logger
	 */
	public static void setLogger(ILogger logger) {
		Logger.logger = logger;
	}

	/**
	 * Logs a message for a class by using the specified log level.
	 * 
	 * @param level
	 * @param clazz
	 * @param message
	 */
	public static void log(Level level, Class<?> clazz, String message) {
		Logger.logger.log(level, clazz, message);
	}

	/**
	 * Logs a message by using the error log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void logError(Class<?> clazz, String message) {
		Logger.logger.logError(clazz, message);
	}

	/**
	 * Logs a message by using the warn log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void logWarn(Class<?> clazz, String message) {
		Logger.logger.logWarn(clazz, message);
	}

	/**
	 * Logs a message by using the info log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void logInfo(Class<?> clazz, String message) {
		Logger.logger.logInfo(clazz, message);
	}

	/**
	 * Logs a message by using the trace log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void logTrace(Class<?> clazz, String message) {
		Logger.logger.logTrace(clazz, message);
	}

	/**
	 * Logs a message by using the debug log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void logDebug(Class<?> clazz, String message) {
		Logger.logger.logDebug(clazz, message);
	}
	
	/**
	 * Logs a message for a object by using the specified log level.
	 * 
	 * @param level
	 * @param obj
	 * @param message
	 */
	public static void log(Level level, Object obj, String message) {
		Logger.logger.log(level, obj, message);
	}

	/**
	 * Logs a message by using the error log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public static void logError(Object obj, String message) {
		Logger.logger.logError(obj, message);
	}

	/**
	 * Logs a message by using the warn log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public static void logWarn(Object obj, String message) {
		Logger.logger.logWarn(obj, message);
	}

	/**
	 * Logs a message by using the info log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public static void logInfo(Object obj, String message) {
		Logger.logger.logInfo(obj, message);
	}

	/**
	 * Logs a message by using the trace log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public static void logTrace(Object obj, String message) {
		Logger.logger.logTrace(obj, message);
	}

	/**
	 * Logs a message by using the debug log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public static void logDebug(Object obj, String message) {
		Logger.logger.logDebug(obj, message);
	}

}
