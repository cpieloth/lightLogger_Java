package executor.lightLogger.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import executor.lightLogger.Level;

public class FileLogger extends AbstractLogger {

	private PrintStream out;

	private static final String FILE = "test.log";

	public FileLogger(String source) {
		super(source);

		File file = new File(FILE);
		if (!file.exists()) {
			try {
				if (file.createNewFile())
					out = new PrintStream(file);
			} catch (IOException e) {
				System.err.println(FileLogger.class.getSimpleName()
						+ ": Could not get write access to file!");
				out = System.out;
			}
		} else if (file.canWrite()) {
			try {
				out = new PrintStream(file);
			} catch (FileNotFoundException e) {
				System.err.println(FileLogger.class.getSimpleName()
						+ ": Could not get write access to file!");
				out = System.out;
			}
		} else {
			System.err.println(FileLogger.class.getSimpleName()
					+ ": Could not get write access to file!");
			out = System.out;
		}

	}

	@Override
	protected void finalize() throws Throwable {
		if (out != null)
			out.close();
		super.finalize();
	}

	private void log(PrintStream ps, Level level, String source, String message) {
		if (this.evaluate(level))
			ps.println("[" + level.getLabel() + "] " + source + ": " + message);
	}

	/* TODO change to private */
	public void log(Level level, String source, String message) {
			this.log(out, level, source, message);
	}

	@Override
	public void log(Level level, String message) {
		log(level, name, message);
	}

	@Override
	public void fatal(String message) {
		/* TODO change to fatal level */
		log(Level.Default.ERROR.getInstance(), name, message);
	}

	@Override
	public void error(String message) {
		log(Level.Default.ERROR.getInstance(), name, message);
	}

	@Override
	public void warn(String message) {
		log(Level.Default.WARN.getInstance(), name, message);
	}

	@Override
	public void info(String message) {
		log(Level.Default.INFO.getInstance(), name, message);
	}

	@Override
	public void debug(String message) {
		log(Level.Default.DEBUG.getInstance(), name, message);
	}

	@Override
	public void trace(String message) {
		log(Level.Default.TRACE.getInstance(), name, message);
	}

	/* ------ OLD ------ */

	@Override
	public void log(Level level, Object obj, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logError(Object obj, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logWarn(Object obj, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logInfo(Object obj, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logTrace(Object obj, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logDebug(Object obj, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void log(Level level, Class<?> clazz, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logError(Class<?> clazz, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logWarn(Class<?> clazz, String message) {
		log(out, Level.Default.ERROR.getInstance(), this.getClass()
				.getSimpleName(), " Method not anymore supported!");
	}

	@Override
	public void logInfo(Class<?> clazz, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logTrace(Class<?> clazz, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logDebug(Class<?> clazz, String message) {
		// TODO Auto-generated method stub

	}

}
