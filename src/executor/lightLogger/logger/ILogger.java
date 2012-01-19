package executor.lightLogger.logger;

import java.util.Properties;
import java.util.Set;

import executor.lightLogger.formatter.IFormatter;
import executor.lightLogger.level.ILevel;

public interface ILogger {

	public static final String UNKNOWN_NAME = "UNKNOWN_NAME";
	
	public boolean loadProperties(Properties properties);

	public String getName();

	public void setName(String name);	

	public IFormatter getFormatter();
	
	public void setFormatter(IFormatter formatter);

	/**
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

	public void setLogMask(ILevel value);

	/**
	 * Sets the log mask for log level selection.
	 * 
	 * @param value
	 *            Log mask as value.
	 */
	public void setLogMask(int value);

	/**
	 * Checks if level is in logmask.
	 * 
	 * @param level
	 * @return true if level is in logmask.
	 */
	public boolean evaluate(ILevel level);

	public void log(ILevel level, String message);

	public void fatal(String message);

	public void error(String message);

	public void warn(String message);

	public void info(String message);

	public void debug(String message);

	public void trace(String message);

}
