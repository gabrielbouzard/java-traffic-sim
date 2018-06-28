package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

final class ChangeTrafficLightGreenTimeCmd implements Command {
	ParameterSet mps = ParameterSet.getSingleton();
	private Double min;
	private Double max;
	
	ChangeTrafficLightGreenTimeCmd(Double min, Double max) {
		this.min = min;
		this.max = max;
	}

	public boolean run() {
		try {
			mps.change("Traffic light green time (seconds)", new MinMax(min, max));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

