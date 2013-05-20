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
	
	@SuppressWarnings("null")
	private static void doLogCalls(ILogger log) {
		System.out.println("LogMask: " + log.getLogMask());
		log.fatal("fatal crash");
		log.error("error occurred");
		Object o = null;
		try {
			o.toString();
		} catch(Exception e) {
			log.error("Error on calling toString()", e);
		}
		log.warn("warning about coffee stock");
		log.info("coffee is ready");
		log.debug("debug problems");
		log.trace("trace method calls");
		log.log(new Level("COOKIES!", 256), "foo barrr");
	}

}
