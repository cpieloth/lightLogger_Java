package executor.lightLogger.level;


public class Level implements ILevel {
	
	private final int value;
	private final String label;
	
	public Level(int value, String label) {
		this.value = value;
		this.label = label;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}
