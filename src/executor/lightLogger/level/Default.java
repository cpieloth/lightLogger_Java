package executor.lightLogger.level;


public enum Default implements ILevel {
	OFF(0, "OFF"), FATAL(1, "FATAL"),
	ERROR(2, "ERROR"), WARN(4, "WARN"), INFO(8, "INFO"), DEBUG(
			16, "DEBUG"), TRACE(32, "TRACE"), ALL(Integer.MAX_VALUE, "ALL");

	private final int value;
	private final String label;

	private Default(int value, String label) {
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
