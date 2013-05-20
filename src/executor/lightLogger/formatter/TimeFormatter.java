package executor.lightLogger.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import executor.lightLogger.level.ILevel;

public class TimeFormatter extends AFormatter {

	private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
	
	@Override
	public String format(ILevel level, String name, Object message) {
		return time.format(new Date()) + " [" + level.getName() + "] " + name + ": " + message;
	}

}
