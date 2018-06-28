package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

import gabriel_bouzard_myproject.model.Model;
import gabriel_bouzard_myproject.model.swing.SwingAnimatorBuilder;

final class RunSimCmd implements Command {
	private ParameterSet mps = ParameterSet.getSingleton();
	
	RunSimCmd() { }

	public boolean run() {
//		try {
			Model m = new Model(new SwingAnimatorBuilder());
			m.run((int)Math.rint(mps.get("Simulation run time (seconds)").getMin()));
			return true;
	}
	
}
