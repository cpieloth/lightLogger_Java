package executor.lightLogger.logger;

import java.util.Properties;
import java.util.Set;

import executor.lightLogger.level.ILevel;

public interface ILogger {
	
	public static final String UNKNOWN_NAME = "UNKNOWN_NAME";

	public boolean loadProperties(Properties properties);

	public String getName();

	public void setName(String name);

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

	public void log(ILevel level, Object message);

	public void fatal(Object message);

	public void error(Object message);

	public void warn(Object message);

	public void info(Object message);

	public void debug(Object message);

	public void trace(Object message);

}
