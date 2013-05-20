package executor.lightLogger.formatter;

import executor.lightLogger.level.ILevel;

public interface IFormatter {
	
	public String format(ILevel level, String name, Object message);
	
	public String format(ILevel level, Object message);

}
