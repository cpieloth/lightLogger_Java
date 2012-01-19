package executor.lightLogger.logger;

import java.util.Set;

import executor.lightLogger.IClassLogger;
import executor.lightLogger.IObjectLogger;
import executor.lightLogger.Level;


public interface ILogger extends IObjectLogger, IClassLogger/*, IMaskable*/ {

	public void log(Level level, String source, String message);
	
	public void log(Level level, String message);
	
	public void fatal(String message);
	
	public void error(String message);
	
	public void warn(String message);
	
	public void info(String message);
	
	public void debug(String message);
	
	public void trace(String message);
	
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
	public void setLogMask(Set<Level> level);

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
	public boolean evaluate(Level level);
	
}
