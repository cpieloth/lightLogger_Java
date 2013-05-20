package executor.lightLogger.logger;

import executor.lightLogger.formatter.IFormatter;

public interface ILoggerFormatable extends ILogger {
	
	public IFormatter getFormatter();

	public void setFormatter(IFormatter formatter);

}
