package executor.lightLogger;

import java.io.PrintStream;
import java.util.Set;

import executor.lightLogger.logger.ILogger;

/**
 * Basic implementation of ILogger. Output on stdo. Format: [Level.getLabel()]
 * class: message
 * 
 * @author executor
 * 
 */
public class BasicLogger implements ILogger {
	
	protected int logMask = Level.Default.ALL.getInstance().getValue();

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

	private void log(PrintStream ps, Level level, String source, String message) {
		if (this.evaluate(level))
			ps.println("[" + level.getLabel() + "] " + source + ": " + message);
	}
	
	@Override
	public void log(Level level, String source, String message) {
		if(Level.Default.ERROR.getValue() == level.getValue())
			this.log(System.err, level, source, message);
		else
			this.log(System.out, level, source, message);
	}

	@Override
	public void log(Level level, Class<?> clazz, String message) {
		if (clazz != null)
			this.log(level, clazz.getName(), message);
		else
			this.log(level, "null", message);
	}

	@Override
	public void logError(Class<?> clazz, String message) {
		this.log(Level.Default.ERROR.getInstance(), clazz.getName(),
				message);
	}

	@Override
	public void logWarn(Class<?> clazz, String message) {
		this.log(Level.Default.WARN.getInstance(), clazz, message);

	}

	@Override
	public void logInfo(Class<?> clazz, String message) {
		this.log(Level.Default.INFO.getInstance(), clazz, message);
	}

	@Override
	public void logTrace(Class<?> clazz, String message) {
		this.log(Level.Default.TRACE.getInstance(), clazz, message);
	}

	@Override
	public void logDebug(Class<?> clazz, String message) {
		this.log(Level.Default.DEBUG.getInstance(), clazz, message);
	}

	@Override
	public void log(Level level, Object obj, String message) {
		if (obj != null)
			this.log(level, obj.getClass().getName(), message);
		else
			this.log(level, "null", message);
	}

	@Override
	public void logError(Object obj, String message) {
		this.log(Level.Default.ERROR.getInstance(), obj.getClass()
				.getName(), message);

	}

	@Override
	public void logWarn(Object obj, String message) {
		this.log(Level.Default.WARN.getInstance(), obj, message);
	}

	@Override
	public void logInfo(Object obj, String message) {
		this.log(Level.Default.INFO.getInstance(), obj, message);
	}

	@Override
	public void logTrace(Object obj, String message) {
		this.log(Level.Default.TRACE.getInstance(), obj, message);
	}

	@Override
	public void logDebug(Object obj, String message) {
		this.log(Level.Default.DEBUG.getInstance(), obj, message);
	}

	@Override
	public void log(Level level, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String message) {
		// TODO Auto-generated method stub
		
	}
}
