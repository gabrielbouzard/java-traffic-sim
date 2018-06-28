package gabriel_bouzard_myproject.cmd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Static class for model parameters.
 */
public class ParameterSet {
	
	private Map<String, MinMax> data = new HashMap<String, MinMax>();
	private static ParameterSet ref;
	
	ParameterSet() {
		data.put("Simulation time step (seconds)", new MinMax(0.1, null));
		data.put("Simulation run time (seconds)", new MinMax(1000.0, null));
		data.put("Grid size (number of roads)", new MinMax(2.0, 4.0));
		data.put("Traffic pattern", new MinMax(1.0, null));
		data.put("Car entry rate (seconds/car)", new MinMax(1.0, 60.0));
		data.put("Road segment length (meters)", new MinMax(200.0, 500.0));
		data.put("Intersection length (meters)", new MinMax(10.0, 15.0));
		data.put("Car length (meters)", new MinMax(5.0, 10.0));
		data.put("Car maximum velocity (meters/second)", new MinMax(4.0, 5.0));
		data.put("Car stop distance (meters)", new MinMax(1.0, 3.0));
		data.put("Car brake distance (meters)", new MinMax(9.0, 10.0));		   
		data.put("Traffic light green time (seconds)", new MinMax(30.0, 180.0));
		data.put("Traffic light yellow time (seconds)", new MinMax(5.0, 10.0));
	}

	public MinMax get(String s) {
		return data.get(s);
	}
	
	public static ParameterSet getSingleton() {
		if (ref == null) {
			ref = new ParameterSet();
		}
		return ref;
	}
	
	 public Object clone() throws CloneNotSupportedException {
		 throw new CloneNotSupportedException(); 
	 }
	
	public MinMax change(String i, MinMax p) {
		if (p.getMin() < 0 || p.getMin() < 0) {
			throw new IllegalArgumentException();
		} else { 
			return data.put(i, p);
		}
	}
	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Parameters:\n\n");
		
		buffer.append("Simulation time step (seconds)  [");
		buffer.append(data.get("Simulation time step (seconds)").getMin() + "]\n");
		
		buffer.append("Simulation run time (seconds)  [");
		buffer.append(data.get("Simulation run time (seconds)").getMin() + "]\n");
		 
		buffer.append("Grid size (number of roads)  [");
		buffer.append(data.get("Grid size (number of roads)").getMin()+","+data.get("Grid size (number of roads)").getMax()+"]\n");
		
		buffer.append("Traffic pattern  [");
		buffer.append(data.get("Traffic pattern").getMin() + "]\n");

		buffer.append("Car entry rate (seconds/car)  [");
		buffer.append(data.get("Car entry rate (seconds/car)").getMin()+","+data.get("Car entry rate (seconds/car)").getMax()+"]\n");

		buffer.append("Road segment length (meters)  [");
		buffer.append(data.get("Road segment length (meters)").getMin()+","+data.get("Road segment length (meters)").getMax()+"]\n");
		
		buffer.append("Intersection length (meters)  [");
		buffer.append(data.get("Intersection length (meters)").getMin()+","+data.get("Intersection length (meters)").getMax()+"]\n");

		buffer.append("Car length (meters)  [");
		buffer.append(data.get("Car length (meters)").getMin()+","+data.get("Car length (meters)").getMax()+"]\n");

		buffer.append("Car maximum velocity (meters/second)  [");
		buffer.append(data.get("Car maximum velocity (meters/second)").getMin()+","+data.get("Car maximum velocity (meters/second)").getMax()+"]\n");
		
		buffer.append("Car stop distance (meters)  [");
		buffer.append(data.get("Car stop distance (meters)").getMin()+","+data.get("Car stop distance (meters)").getMax()+"]\n");

		buffer.append("Car brake distance (meters)  [");
		buffer.append(data.get("Car brake distance (meters)").getMin()+","+data.get("Car brake distance (meters)").getMax()+"]\n");

		buffer.append("Traffic light green time (seconds)  [");
		buffer.append(data.get("Traffic light green time (seconds)").getMin()+","+data.get("Traffic light green time (seconds)").getMax()+"]\n");

		buffer.append("Traffic light yellow time (seconds)  [");
		buffer.append(data.get("Traffic light yellow time (seconds)").getMin()+","+data.get("Traffic light yellow time (seconds)").getMax()+"]\n");

		return buffer.toString();
	}
	
	public HashMap<String, MinMax> data() {
		return (HashMap<String, MinMax>)data;
	}

}

