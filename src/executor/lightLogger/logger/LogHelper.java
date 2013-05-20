package executor.lightLogger.logger;

import java.util.Set;

import executor.lightLogger.level.ILevel;

public class LogHelper {

	/**
	 * Checks if level is in logmask.
	 * 
	 * @param level
	 * @return true if level is in logmask.
	 */
	public static boolean evaluate(ILevel level, int logMask) {
		return (logMask & level.getValue()) == level.getValue();
	}

	public static int getLogMask(Set<ILevel> level) {
		int logMask = 0;
		int tmp;
		for (ILevel lvl : level) {
			tmp = logMask;
			logMask += lvl.getValue();
			// Check for int overflow
			if (logMask < tmp) {
				logMask = tmp;
				return logMask;
			}
		}
		return logMask;
	}
	
}
