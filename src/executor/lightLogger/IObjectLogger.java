package executor.lightLogger;

public interface IObjectLogger extends IMaskable {

	@Deprecated
	/**
	 * Logs a message for a object by using the specified log level.
	 * 
	 * @param level
	 * @param obj
	 * @param message
	 */
	public void log(Level level, Object obj, String message);

	@Deprecated
	/**
	 * Logs a message by using the error log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public void logError(Object obj, String message);

	@Deprecated
	/**
	 * Logs a message by using the warn log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public void logWarn(Object obj, String message);

	@Deprecated
	/**
	 * Logs a message by using the info log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public void logInfo(Object obj, String message);

	@Deprecated
	/**
	 * Logs a message by using the trace log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public void logTrace(Object obj, String message);

	@Deprecated
	/**
	 * Logs a message by using the debug log level.
	 * 
	 * @param obj
	 * @param message
	 */
	public void logDebug(Object obj, String message);
	
}
