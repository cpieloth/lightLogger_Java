package executor.lightLogger.level;

/**
 * Standard implementation of ILevel.
 * 
 * @author executor
 * 
 */
public class Level implements ILevel {

	private final int value;
	private final String name;

	public Level(String name, int value) {
		this.value = value;
		this.name = name;
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
