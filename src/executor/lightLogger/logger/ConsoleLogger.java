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

	public static enum Config {
		FORMATTER(ConsoleLogger.class.getSimpleName() + ".formatter",
				BasicFormatter.class.getName());

		public final String key;
		public final String defValue;

		Config(String key, String val) {
			this.key = key;
			this.defValue = val;
		}
	}
	
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
	public boolean loadProperties(Properties properties) {
		if (properties == null) {
			return false;
		}

		boolean success = true;

		String val;
		try {
			val = properties.getProperty(Config.FORMATTER.key,
					Config.FORMATTER.defValue);
			formatter = ((Class<? extends IFormatter>) Class.forName(val))
					.newInstance();
		} catch (Exception e) {
			Logger.error(ALoggerFormatable.class,
					"Could instanciate formatter!");
			success = false;
		}

		return success;
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
