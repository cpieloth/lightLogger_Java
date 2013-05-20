package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;

/**
 * Interface for string formated log messages.
 * 
 * @author executor
 *
 */
public interface IFormatter {
	
	/**
	 * Formats a log message.
	 * 
	 * @param level Level to log
	 * @param name Name of the logger
	 * @param message Message to log
	 * @return formatted string
	 */
	public String format(ILevel level, String name, Object message);
	
	/**
	 * Formats a log message.
	 * 
	 * @param level Level to log
	 * @param name Name of the logger
	 * @param message Message to log
	 * @param throwable Throwable instance
	 * @return formatted string
	 */
	public String format(ILevel level, String name, Object message, Throwable throwable);

}
