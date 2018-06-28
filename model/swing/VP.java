package gabriel_bouzard_myproject.model.swing;

import gabriel_bouzard_myproject.cmd.ParameterSet;
/**
 * Static class for visualization parameters.
 */
class VP {
	private VP() {	}
	static ParameterSet mps = ParameterSet.getSingleton();
	/** Width of model elements, in meters */
	static double elementWidth = mps.get("Car length (meters)").getMax();
	/** Gap between model elements, in meters */
	static double gap = 1;
	/** Width of the displayed graphics window, in pixels */
	static int displayWidth = (int)((mps.get("Road segment length (meters)").getMax() / 3) * (mps.get("Grid size (number of roads)").getMax() + 1.24));
	/** Height of the displayed graphics window, in pixels */
	static int displayHeight = (int)((mps.get("Road segment length (meters)").getMax() / 3) * (mps.get("Grid size (number of roads)").getMin() + 1.18));
	/** Delay introduced into each graphics update, in milliseconds */
	static int displayDelay = 50;
	/** Scalefactor relating model space to pixels, in pixels/meter */
	static double scaleFactor = 1;
}
