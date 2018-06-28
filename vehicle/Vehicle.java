package gabriel_bouzard_myproject.vehicle;

import java.util.LinkedList;

import gabriel_bouzard_myproject.waypoint.Waypoint;

public interface Vehicle {
	public void setWaypoint(Waypoint w);
	public void resetPosition();
	public double position();
	/* If one wanted to extend, it would be easy to change to VehicleUpdateStrategy */
	public void updatePosition(CarUpdateStrategy strat, LinkedList<Vehicle> vehicles); 
	public double length();
	public double stopDistance();
	public boolean isHorizontal();
	public Object getColor();
}
