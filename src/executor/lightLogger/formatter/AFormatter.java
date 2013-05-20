package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;

/**
 * Helper class for implementations of IFormat.
 * Throwable format: FORMAT - THROWABLE.getMessage()\nTHROWABLE.toString()
 * 
 * @author executor
 *
 */
public abstract class AFormatter implements IFormatter {

	@Override
	public String format(ILevel level, String name, Object message,
			Throwable throwable) {
		return this.format(level, name, message) + " - "
				+ throwable.getMessage() + "\n" + throwable.toString();
	}

}
