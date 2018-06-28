package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

final class ChangeTrafficPatternCmd implements Command {
	private ParameterSet mps = ParameterSet.getSingleton();
	private Double pattern;
	
	ChangeTrafficPatternCmd(Double pattern) {
		this.pattern = pattern;
	}

	public boolean run() {
		try {
			mps.change("Traffic pattern", new MinMax(pattern, null));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
