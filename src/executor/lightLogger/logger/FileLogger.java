package executor.lightLogger.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import executor.lightLogger.Logger;
import executor.lightLogger.level.Default;
import executor.lightLogger.level.ILevel;

public class FileLogger extends AbstractLogger {

	public static enum Config {
		FILENAME(FileLogger.class.getSimpleName() + ".file", FileLogger.class
				.getSimpleName() + ".log");

		public final String key;
		public final String defValue;

		Config(String key, String val) {
			this.key = key;
			this.defValue = val;
		}
	}

	private Writer out = null;

	public FileLogger() {
		this(ILogger.UNKNOWN_NAME);
	}

	public FileLogger(String source) {
		super(source);
	}

	@Override
	protected void finalize() throws Throwable {
		if (out != null)
			out.close();
		super.finalize();
	}

	@Override
	public void log(ILevel level, String message) {
		super.log(out, level, message);
	}

	@Override
	public void fatal(String message) {
		log(Default.FATAL, message);
	}

	@Override
	public void error(String message) {
		log(Default.ERROR, message);
	}

	@Override
	public void warn(String message) {
		log(Default.WARN, message);
	}

	@Override
	public void info(String message) {
		log(Default.INFO, message);
	}

	@Override
	public void debug(String message) {
		log(Default.DEBUG, message);
	}

	@Override
	public void trace(String message) {
		log(Default.TRACE, message);
	}

	@Override
	public boolean loadProperties(Properties properties) {
		boolean success = true;
		if (properties == null) {
			success = false;
			return success;
		}

		String val = properties.getProperty(Config.FILENAME.key,
				Config.FILENAME.defValue);

		try {
			out = new BufferedWriter(new FileWriter(val, true));
		} catch (Exception e) {
			success = false;
		}

		if (!success) {
			out = new OutputStreamWriter(System.out);
			Logger.error(FileLogger.class,
					"Could not open file! Using System.out instead.");
		}

		return success;
	}

}
