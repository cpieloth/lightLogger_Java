package executor.lightLogger.logger;

import executor.lightLogger.formatter.BasicFormatter;
import executor.lightLogger.formatter.IFormatter;


/**
 * Helper class for implementations of ILoggerFormatable.
 * 
 * @author executor
 *
 */
public abstract class ALoggerFormatable extends ALogger implements
		ILoggerFormatable {

	protected IFormatter formatter = new BasicFormatter();

	public ALoggerFormatable() {
		this(ILogger.UNKNOWN_NAME);
	}

	public ALoggerFormatable(String name) {
		this(name, Integer.MAX_VALUE);
	}

	public ALoggerFormatable(String name, int logMask) {
		super(name, logMask);
	}

	@Override
	public IFormatter getFormatter() {
		return formatter;
	}

	@Override
	public void setFormatter(IFormatter formatter) {
		this.formatter = formatter;
	}
	
}
