package executor.lightLogger.level;

/**
 * Interface for a log level. A ILogger can filter log message with the help of
 * log levels. User implementations must not use values from Default Levels.
 * 
 * @author executor
 * 
 */
public interface ILevel {

	/**
	 * Value is used to filter log levels. A value must not be 0 and must be
	 * 2^x, e.g. 1, 2, 4 ...
	 * 
	 * @return value
	 */
	public int getValue();

	/**
	 * Name of the level to print.
	 * 
	 * @return name
	 */
	public String getName();
}
