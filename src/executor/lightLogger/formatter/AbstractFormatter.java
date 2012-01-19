package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;
import executor.lightLogger.logger.ILogger;

public abstract class AbstractFormatter implements IFormatter {

	@Override
	public String format(ILevel level, String message) {
		return format(level, ILogger.UNKNOWN_NAME, message);
	}

}
