package executor.lightLogger.logger;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import executor.lightLogger.Logger;
import executor.lightLogger.formatter.BasicFormatter;
import executor.lightLogger.formatter.IFormatter;
import executor.lightLogger.level.Default;
import executor.lightLogger.level.ILevel;

public class ConsoleLogger extends ALoggerWriter {
	// NOTE: Do not close writer which contain System.out/System.err, otherwise all syso's won't work!
	private Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
	private Writer err = new BufferedWriter(new OutputStreamWriter(System.err));

	public ConsoleLogger() {
		super();
	}

	public ConsoleLogger(String name) {
		super(name);
	}

	@Override
	public void log(ILevel level, Object message) {
		if (Default.ERROR.getValue() == level.getValue() || Default.FATAL.getValue() == level.getValue())
			super.log(err, level, message);
		else
			super.log(out, level, message);
	}
	
	@Override
	public void log(ILevel level, Object message, Throwable throwable) {
		if (Default.ERROR.getValue() == level.getValue() || Default.FATAL.getValue() == level.getValue())
			super.log(err, level, message, throwable);
		else
			super.log(out, level, message, throwable);
	}
}
