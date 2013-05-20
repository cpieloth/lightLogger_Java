package executor.lightLogger.logger;

import java.util.Properties;
import java.util.Set;

import executor.lightLogger.level.ILevel;

/**
 * Interface for a logger. Implementations can log a message to a file,
 * database, console and more. Log messages are filtered by a log mask. A log
 * mask is used like a bit mask. Each level is one bit in the log mask.
 * 
 * @author executor
 * 
 */
public interface ILogger {

	public static final String UNKNOWN_NAME = "UNKNOWN_NAME";

	/**
	 * Initializes the logger with settings defined by the implementation.
	 * 
	 * @param properties
	 *            Ready to read properties instance,
	 * @return true if all properties are loaded.
	 */
	public boolean loadProperties(Properties properties);

	/**
	 * Gets the name of the logger. Identifier for the log messages, e.g. class
	 * name.
	 * 
	 * @return name
	 */
	public String getName();

	/**
	 * Sets the name for the logger. Identifier for the log messages, e.g. class
	 * name.
	 * 
	 * @param name
	 *            Name to set.
	 */
	public void setName(String name);

	/**
	 * Gets the log mask of this logger.
	 * 
	 * @return The log mask for log level selection.
	 */
	public int getLogMask();

	/**
	 * Sets the log mask for log level selection by summation of each level's
	 * value.
	 * 
	 * @param level
	 *            Set of levels to log
	 */
	public void setLogMask(Set<ILevel> level);

	/**
	 * Sets the log mask to one level.
	 * 
	 * @param value
	 *            level to set
	 */
	public void setLogMask(ILevel value);

	/**
	 * Sets the log mask for log level selection.
	 * 
	 * @param value
	 *            Log mask as value.
	 */
	public void setLogMask(int value);

	/**
	 * Log a message to a specific level.
	 * 
	 * @param level
	 * @param message
	 */
	public void log(ILevel level, Object message);

	/**
	 * Logs a message and throwable instance to a specific level.
	 * 
	 * @param level
	 * @param message
	 * @param throwable
	 */
	public void log(ILevel level, Object message, Throwable throwable);

	/**
	 * Logs a message with big impact to the application. Usually the
	 * application must be shutdown.
	 * 
	 * @param message
	 */
	public void fatal(Object message);

	public void fatal(Object message, Throwable throwable);

	/**
	 * Logs an error message. Usually the application must not be shutdown, if
	 * the error are handled.
	 * 
	 * @param message
	 */
	public void error(Object message);

	public void error(Object message, Throwable throwable);

	/**
	 * Logs a warning message. The application is able to run.
	 * 
	 * @param message
	 */
	public void warn(Object message);

	public void warn(Object message, Throwable throwable);

	/**
	 * Logs an info message.
	 * 
	 * @param message
	 */
	public void info(Object message);

	/**
	 * Logs messages for the developer and debugging purpose. This level should
	 * be disable on release.
	 * 
	 * @param message
	 */
	public void debug(Object message);

	/**
	 * Like debug, logs message to trace the program flow e.g. function calls.
	 * 
	 * @param message
	 */
	public void trace(Object message);

}
