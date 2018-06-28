package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

final class ChangeTrafficLightYellowTimeCmd implements Command {
	private ParameterSet mps = ParameterSet.getSingleton();
	private Double min;
	private Double max;
	
	ChangeTrafficLightYellowTimeCmd(Double min, Double max) {
		this.min = min;
		this.max = max;
	}

	public boolean run() {
		try {
			mps.change("Traffic light yellow time (seconds)", new MinMax(min, max));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

