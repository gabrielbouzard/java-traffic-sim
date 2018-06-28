package gabriel_bouzard_myproject.model.swing;

import gabriel_bouzard_myproject.cmd.ParameterSet;

/**
 * Static class for model parameters.
 */
public class MP {
	private MP() {}
	static ParameterSet mps = ParameterSet.getSingleton();

	/** Length of roads, in meters */
	public static double roadLength = mps.get("Road segment length (meters)").getMax() / 3;
	/** Length of cars, in meters */
	//if (mps.get("Car length (meters)").getMax() )
	public static double carLength = mps.get("Car length (meters)").getMax(); //roadLength / 28;
	public static double intersectionLength = 7;
	
	
}
