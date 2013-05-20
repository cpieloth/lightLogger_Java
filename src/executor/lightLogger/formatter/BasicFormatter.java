package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;

/**
 * Formats a log message to:
 * [LEVEL] NAME: MESSAGE
 * 
 * @author executor
 *
 */
public class BasicFormatter extends AFormatter {

	@Override
	public String format(ILevel level, String name, Object message) {
		return "[" + level.getName() + "] " + name + ": " + message;
	}

}
