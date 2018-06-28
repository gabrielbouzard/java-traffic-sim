package gabriel_bouzard_myproject.cmd;

/**
 * A simple interface used to implement commands. Contains one method "public boolean run()".  
 */
public interface Command {
	/**
	 * Do the command.
 	 * @return true if command succeeds, false otherwise
 	 */
	public boolean run();
}
