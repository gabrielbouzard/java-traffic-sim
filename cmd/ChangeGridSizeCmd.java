package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;

final class ChangeGridSizeCmd implements Command {
	private ParameterSet mps = ParameterSet.getSingleton();
	private Double rows;
	private Double columns;
	
	ChangeGridSizeCmd(Double p1, Double p2) {
		this.rows = Math.rint(p1);
		this.columns = Math.rint(p2);
	}

	public boolean run() {
		try {
			HashMap<String, MinMax> hm = mps.data();
			hm.put("Grid size (number of roads)", new MinMax(rows, columns));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
