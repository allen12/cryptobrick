package personal.cheng.cryptobrick.shell.command;

public interface CryptobrickCommand 
{
	/** 
	 * Description of the task
	 *
	 * @return
	 */
	public String getDescription();

	/**
	 * Specify the options/arguments for this task to query the user
	 * 
	 * @return
	 */
	public Options getOptions();

	/**
	 * Initialize the task with given parameters
	 * 
	 * @param opts
	 */
	public void initialize(Options opts);

	/**
	 * Run the task
	 */
	public void execute();

	/**
	 * Do any necessary cleanup afterwards
	 */
	public void shutdown();
}
