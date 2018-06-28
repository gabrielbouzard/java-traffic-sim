package gabriel_bouzard_myproject.main;

import gabriel_bouzard_myproject.cmd.CmdFact;
import gabriel_bouzard_myproject.model.Model;
import gabriel_bouzard_myproject.model.swing.SwingAnimatorBuilder;
import gabriel_bouzard_myproject.ui.PopupUI;
import gabriel_bouzard_myproject.ui.TextUI;
import gabriel_bouzard_myproject.ui.UI;
import gabriel_bouzard_myproject.ui.UIMenu;

/**
 * A static class to launch the simulation.
 */
public class Main {
	private Main() {}
	public static void main(String[] args) {	
		UI ui = null;

		if (args.length > 0) {
			if ("GUI".equalsIgnoreCase(args[0])) {
				ui = new PopupUI();
			} else if ("TEXT".equalsIgnoreCase(args[0])) {
				ui = new TextUI();
			} else {
				System.out.println("Argument must be GUI or TEXT");
				System.exit(1);
			}
		} else {
			// if-else construct determines a random user interface
			if (Math.random() <= 0.01) {
				ui = new TextUI();
			} else {
				ui = new PopupUI();
			}
		}
		
		Control control = new Control(ui);
		control.run();
		System.exit(0);
		
	}	 
}


