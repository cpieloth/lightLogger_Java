package executor.lightLogger.logger;

import java.util.Set;

import executor.lightLogger.Level;

/**
 * Basic implementation of ILogger. Output on stdo. Format: [Level.getLabel()]
 * class: message
 * 
 * @author executor
 * 
 */
public abstract class AbstractLogger implements ILogger {
	
	protected int logMask = Level.Default.ALL.getInstance().getValue();
	
	protected String name;
	
	public AbstractLogger(String name) {
		this(name, Integer.MAX_VALUE);
	}
	
	public AbstractLogger(String name, int logMask) {
		this.name = name;
		this.logMask = logMask;
	}

	@Override
	public int getLogMask() {
		return this.logMask;
	}

	@Override
	public void setLogMask(Set<Level> level) {
		this.logMask = 0;
		int tmp;
		for (Level lvl : level) {
			tmp = this.logMask;
			this.logMask += lvl.getValue();
			if (this.logMask < tmp) {
				this.logMask = tmp;
				return;
			}
		}
	}

	@Override
	public void setLogMask(int value) {
		this.logMask = value;
	}
	
	@Override
	public boolean evaluate(Level level) {
		return (this.logMask & level.getValue()) == level.getValue();
	}

}
