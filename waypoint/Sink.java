package gabriel_bouzard_myproject.waypoint;

import gabriel_bouzard_myproject.light.Light;
import gabriel_bouzard_myproject.light.RYGLight;
import gabriel_bouzard_myproject.vehicle.Car;
import gabriel_bouzard_myproject.vehicle.Vehicle;

public class Sink implements Waypoint {
	Sink() { }
	
	public boolean accept(Vehicle v) { return true; }
	
	public void setNext(Waypoint w) { return; }

	public Double length() {	return null; }

	public Light nextLight() { return null; }
	
	public boolean full() { return false; }
	
}
