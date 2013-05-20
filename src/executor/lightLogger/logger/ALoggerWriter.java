package executor.lightLogger.logger;

import java.io.IOException;
import java.io.Writer;

import executor.lightLogger.Logger;
import executor.lightLogger.level.Default;
import executor.lightLogger.level.ILevel;

public abstract class ALoggerWriter extends ALoggerFormatable {

	public ALoggerWriter() {
		this(ILogger.UNKNOWN_NAME);
	}

	public ALoggerWriter(String name) {
		this(name, Integer.MAX_VALUE);
	}

	public ALoggerWriter(String name, int logMask) {
		super(name, logMask);
	}

	@Override
	public void fatal(Object message) {
		log(Default.FATAL, message);
	}

	@Override
	public void fatal(Object message, Throwable throwable) {
		log(Default.FATAL, message, throwable);
	}

	@Override
	public void error(Object message) {
		log(Default.ERROR, message);
	}

	@Override
	public void error(Object message, Throwable throwable) {
		log(Default.ERROR, message, throwable);
	}

	@Override
	public void warn(Object message) {
		log(Default.WARN, message);
	}

	@Override
	public void warn(Object message, Throwable throwable) {
		log(Default.WARN, message, throwable);
	}

	@Override
	public void info(Object message) {
		log(Default.INFO, message);
	}

	@Override
	public void debug(Object message) {
		log(Default.DEBUG, message);
	}

	@Override
	public void trace(Object message) {
		log(Default.TRACE, message);
	}

	protected void log(Writer out, ILevel level, Object message) {
		if (evaluate(level))
			try {
				out.write(formatter.format(level, name, message) + "\n");
				out.flush();
			} catch (IOException e) {
				Logger.error(ALoggerFormatable.class,
						"Could not write to destination!");
			}
	}

	protected void log(Writer out, ILevel level, Object message,
			Throwable throwable) {
		if (evaluate(level))
			try {
				out.write(formatter.format(level, name, message, throwable)
						+ "\n");
				out.flush();
			} catch (IOException e) {
				Logger.error(ALoggerFormatable.class,
						"Could not write to destination!");
			}
	}

}
