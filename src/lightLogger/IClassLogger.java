package lightLogger;


/**
 * Interface for log object. An implementation can specify the output format and more.
 * 
 * @author executor
 *
 */
public interface IClassLogger extends IMaskable {

	/**
	 * Logs a message for a class by using the specified log level.
	 * 
	 * @param level
	 * @param clazz
	 * @param message
	 */
	public void log(Level level, Class<?> clazz, String message);

	/**
	 * Logs a message by using the error log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public void logError(Class<?> clazz, String message);

	/**
	 * Logs a message by using the warn log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public void logWarn(Class<?> clazz, String message);

	/**
	 * Logs a message by using the info log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public void logInfo(Class<?> clazz, String message);

	/**
	 * Logs a message by using the trace log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public void logTrace(Class<?> clazz, String message);

	/**
	 * Logs a message by using the debug log level.
	 * 
	 * @param clazz
	 * @param message
	 */
	public void logDebug(Class<?> clazz, String message);

}
