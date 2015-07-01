package personal.cheng.cryptobrick.shell.command;

public interface CryptobrickCommand 
{
	/** 
	 * Description of the task
	 *
	 * @return
	 */
	String getDescription();

	/**
	 * Specify the options/arguments for this task to query the user
	 * 
	 * @return
	 */
	Options getOptions();

	/**
	 * Initialize the task with given parameters
	 * 
	 * @param opts
	 */
	void initialize(Options opts);

	/**
	 * Run the task
	 */
	void execute();

	/**
	 * Do any necessary cleanup afterwards
	 */
	void shutdown();
}
