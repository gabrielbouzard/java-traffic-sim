package gabriel_bouzard_myproject.waypoint;

import gabriel_bouzard_myproject.light.Light;
import gabriel_bouzard_myproject.vehicle.Vehicle;

public interface Waypoint {
	
	public boolean accept(Vehicle v);
	public Double length();
	public Light nextLight();
	public boolean full();
	public void setNext(Waypoint y);
	
}
