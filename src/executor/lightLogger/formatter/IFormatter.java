package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;

public interface IFormatter {
	
	public String format(ILevel level, String name, String message);
	
	public String format(ILevel level, String message);

}
