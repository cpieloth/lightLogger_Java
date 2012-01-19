package lightLogger;

import java.util.HashSet;
import java.util.Set;

/**
 * Example how LightLogger could be used.
 * 
 * @author executor
 *
 */
public class Example {

	public static void main(String[] args) {
		Logger.setLogger(new BasicLogger());
		
		doLogCalls();
		
		Logger.setLogMask(Level.Default.ERROR.getValue());
		
		doLogCalls();
		
		Set<Level> newLogMask = new HashSet<Level>();
		newLogMask.add(Level.Default.INFO.getInstance());
		newLogMask.add(Level.Default.WARN.getInstance());
		Logger.setLogMask(newLogMask);
		
		doLogCalls();
	}
	
	private static void doLogCalls() {
		System.out.println("LogMask: " + Logger.getLogMask());
		Logger.logError(Example.class, "error ...");
		Logger.logWarn(Example.class, "warn ...");
		Logger.logInfo(Example.class, "info ...");
		Logger.logTrace(Example.class, "trace ...");
		Logger.logDebug(Example.class, "debug ...");
		Logger.log(new Level(Level.Default.ERROR.getInstance().getValue(), "E-R-R-O-R"), Example.class, "e-r-r-o-r ...");
	}

}
