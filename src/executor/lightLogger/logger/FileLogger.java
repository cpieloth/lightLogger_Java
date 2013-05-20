package executor.lightLogger.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import executor.lightLogger.Logger;
import executor.lightLogger.formatter.BasicFormatter;
import executor.lightLogger.formatter.IFormatter;
import executor.lightLogger.level.ILevel;

public class FileLogger extends ALoggerWriter {

	public static enum Config {
		FORMATTER(FileLogger.class.getSimpleName() + ".formatter",
				BasicFormatter.class.getName()),
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
	public void log(ILevel level, Object message) {
		super.log(out, level, message);
	}
	
	@Override
	public void log(ILevel level, Object message, Throwable throwable) {
		super.log(out, level, message, throwable);
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

		val = properties.getProperty(Config.FILENAME.key,
				Config.FILENAME.defValue);

		success &= setFile(val);

		if (!success) {
			out = new OutputStreamWriter(System.out);
			Logger.error(FileLogger.class,
					"Could not open file! Using System.out instead.");
		}

		return success;
	}

	public boolean setFile(String file) {
		try {
			if (out != null)
				out.close();

			out = new BufferedWriter(new FileWriter(file, true));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
