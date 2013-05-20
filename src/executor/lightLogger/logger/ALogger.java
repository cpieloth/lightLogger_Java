package executor.lightLogger.logger;

import java.util.Set;

import executor.lightLogger.level.Default;
import executor.lightLogger.level.ILevel;

/**
 * Basic implementation of ILogger.
 * 
 * @author executor
 * 
 */
public abstract class ALogger implements ILogger {

	protected int logMask = Default.ALL.getValue();
	protected String name;

	public ALogger() {
		this(ILogger.UNKNOWN_NAME);
	}

	public ALogger(String name) {
		this(name, Integer.MAX_VALUE);
	}

	public ALogger(String name, int logMask) {
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
	public int getLogMask() {
		return logMask;
	}

	@Override
	public void setLogMask(Set<ILevel> level) {
		this.logMask = LogHelper.getLogMask(level);
	}

	@Override
	public void setLogMask(ILevel level) {
		this.logMask = level.getValue();
	}

	@Override
	public void setLogMask(int value) {
		this.logMask = value;
	}

}
