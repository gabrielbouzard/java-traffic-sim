package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

final class ChangeRunTimeCmd implements Command {
	private ParameterSet mps = ParameterSet.getSingleton();
	private Double time;
	
	ChangeRunTimeCmd(Double time) {
		this.time = time;
	}

	public boolean run() {
		try {
			mps.change("Simulation run time (seconds)", new MinMax(time, null));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
