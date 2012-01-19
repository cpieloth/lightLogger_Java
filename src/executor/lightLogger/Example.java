package executor.lightLogger;

import java.util.HashSet;
import java.util.Set;

import executor.lightLogger.level.Default;
import executor.lightLogger.level.ILevel;
import executor.lightLogger.level.Level;
import executor.lightLogger.logger.ILogger;

/**
 * Example how LightLogger could be used.
 * 
 * @author executor
 *
 */
public class Example {

	public static void main(String[] args) {
		ILogger log = Logger.getInstance(Example.class);
		
		doLogCalls(log);
		
		log.setLogMask(Default.ERROR);
		
		doLogCalls(log);
		
		Set<ILevel> newLogMask = new HashSet<ILevel>();
		newLogMask.add(Default.INFO);
		newLogMask.add(Default.WARN);
		log.setLogMask(newLogMask);
		
		doLogCalls(log);
	}
	
	private static void doLogCalls(ILogger log) {
		System.out.println("LogMask: " + log.getLogMask());
		log.fatal("fatal crash");
		log.error("error occurred");
		log.warn("warning about coffee stock");
		log.info("coffee is ready");
		log.debug("debug problems");
		log.trace("trace method calls");
		log.log(new Level(256, "COOKIES!"), "foo barrr");
	}

}
