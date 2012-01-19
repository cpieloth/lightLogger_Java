package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;

public class BasicFormatter extends AbstractFormatter {

	@Override
	public String format(ILevel level, String name, String message) {
		return "[" + level.getName() + "] " + name + ": " + message;
	}

}
