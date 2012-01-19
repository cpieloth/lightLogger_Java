package lightLogger;


public interface ILogger extends IObjectLogger, IClassLogger, IMaskable {

	public void log(Level level, String source, String message);
	
}
