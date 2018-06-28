package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

final class ChangeCarLengthCmd implements Command {
	private ParameterSet mps = ParameterSet.getSingleton();
	private Double min;
	private Double max;
	
	ChangeCarLengthCmd(Double min, Double max) {
		this.min = min;
		this.max = max;
	}

	public boolean run() {
		try {
			mps.change("Car length (meters)", new MinMax(min, max));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
