package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;
import executor.lightLogger.logger.ILogger;

public abstract class AFormatter implements IFormatter {

	@Override
	public String format(ILevel level, Object message) {
		return format(level, ILogger.UNKNOWN_NAME, message);
	}

}
