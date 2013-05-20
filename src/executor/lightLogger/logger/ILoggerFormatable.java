package executor.lightLogger.logger;

import executor.lightLogger.formatter.IFormatter;

/**
 * Extends ILogger to set formats for log messages.
 * 
 * @author executor
 * 
 */
public interface ILoggerFormatable extends ILogger {

	/**
	 * Gets the formatter.
	 * 
	 * @return formatter
	 */
	public IFormatter getFormatter();

	/**
	 * Sets a formatter for this instance.
	 * 
	 * @param formatter
	 *            Formatter to set
	 */
	public void setFormatter(IFormatter formatter);

}
