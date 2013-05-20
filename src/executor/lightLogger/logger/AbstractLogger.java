package executor.lightLogger.logger;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import executor.lightLogger.Logger;
import executor.lightLogger.formatter.IFormatter;
import executor.lightLogger.formatter.TimeFormatter;
import executor.lightLogger.level.Default;
import executor.lightLogger.level.ILevel;

/**
 * Basic implementation of ILogger. Output on stdo. Format: [Level.getLabel()]
 * class: message
 * 
 * @author executor
 * 
 */
public abstract class AbstractLogger implements ILogger {

	protected int logMask = Default.ALL.getValue();
	protected String name;
	protected IFormatter formatter = new TimeFormatter();

	public AbstractLogger() {
		this(ILogger.UNKNOWN_NAME);
	}

	public AbstractLogger(String name) {
		this(name, Integer.MAX_VALUE);
	}

	public AbstractLogger(String name, int logMask) {
		this.name = name;
		this.logMask = logMask;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public IFormatter getFormatter() {
		return formatter;
	}
	
	@Override
	public void setFormatter(IFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public int getLogMask() {
		return logMask;
	}

	@Override
	public void setLogMask(Set<ILevel> level) {
		this.logMask = 0;
		int tmp;
		for (ILevel lvl : level) {
			tmp = this.logMask;
			this.logMask += lvl.getValue();
			if (this.logMask < tmp) {
				this.logMask = tmp;
				return;
			}
		}
	}

	@Override
	public void setLogMask(ILevel level) {
		setLogMask(level.getValue());
	}

	@Override
	public void setLogMask(int value) {
		this.logMask = value;
	}

	@Override
	public boolean evaluate(ILevel level) {
		return (this.logMask & level.getValue()) == level.getValue();
	}

	protected void log(Writer out, ILevel level, Object message) {
		if (this.evaluate(level))
			try {
				out.write(formatter.format(level, name, message) + "\n");
				out.flush();
			} catch (IOException e) {
				Logger.error(AbstractLogger.class,
						"Could not write to destination!");
			}
	}

}
