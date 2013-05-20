package executor.lightLogger.logger;

import java.io.IOException;
import java.io.Writer;

import executor.lightLogger.Logger;
import executor.lightLogger.formatter.BasicFormatter;
import executor.lightLogger.formatter.IFormatter;
import executor.lightLogger.level.ILevel;


/**
 * Basic implementation of ILogger.
 * 
 * Format: [Level.getLabel()] class: message
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

	protected void log(Writer out, ILevel level, Object message) {
		if (LogHelper.evaluate(level, logMask))
			try {
				out.write(formatter.format(level, name, message) + "\n");
				out.flush();
			} catch (IOException e) {
				Logger.error(ALoggerFormatable.class, "Could not write to destination!");
			}
	}
}
