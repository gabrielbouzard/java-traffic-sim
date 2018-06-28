package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

import gabriel_bouzard_myproject.ui.UI;

final class ChangeTimeTimeStepCmd implements Command {
	
	private ParameterSet mps = ParameterSet.getSingleton();
	private Double step;
	
	ChangeTimeTimeStepCmd(Double step) {
		this.step = step;
	}

	public boolean run() {
		try {
			mps.change("Simulation time step (seconds)", new MinMax(step, null));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
