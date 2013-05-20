package executor.lightLogger.level;

/**
 * Definition for default implementations of ILevel.
 * 
 * @author executor
 *
 */
public enum Default implements ILevel {
	OFF(0), FATAL(1), ERROR(2), WARN(4), INFO(8), DEBUG(16), TRACE(32), ALL(
			Integer.MAX_VALUE);

	private final int value;
	private final String name;

	private Default(int value) {
		this.value = value;
		this.name = this.name();
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}
}
