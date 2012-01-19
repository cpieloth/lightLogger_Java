package lightLogger;

import java.util.Set;

public interface IMaskable {

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
