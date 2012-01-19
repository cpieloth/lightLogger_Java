package executor.lightLogger;

/**
 * A log level is used for message selection which should be logged and the
 * output format.
 * 
 * @author executor
 * 
 */
public class Level {

	/**
	 * A set of default log levels.
	 * 
	 * @author executor
	 * 
	 */
	public enum Default {
		ERROR(1, "ERROR"), WARN(2, "WARN"), INFO(4, "INFO"), TRACE(8, "TRACE"), DEBUG(
				16, "DEBUG"), ALL(Integer.MAX_VALUE, "all");

		private int value;
		private String label;

		private Default(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public Level getInstance() {
			return new Level(value, label);
		}

		public int getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.label;
		}

	}

	private int value;
	private String label;

	public Level(int level, String label) {
		this.value = level;
		this.label = label;
	}

	/**
	 * 
	 * @return Value for level selection and log mask.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * 
	 * @return Label or level name.
	 */
	public String getLabel() {
		return this.label;
	}

}
