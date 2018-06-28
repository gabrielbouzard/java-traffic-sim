package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

final class ChangeRoadLengthCmd implements Command {
	private ParameterSet mps = ParameterSet.getSingleton();
	private Double min;
	private Double max;
	
	ChangeRoadLengthCmd(Double p1, Double p2) {
		this.min = p1;
		this.max = p2;
	}

	public boolean run() {
		try {
			mps.change("Road segment length (meters)", new MinMax(min, max));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
