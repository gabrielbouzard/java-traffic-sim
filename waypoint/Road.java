package gabriel_bouzard_myproject.waypoint;

import java.util.List;

import gabriel_bouzard_myproject.vehicle.Car;

public interface Road {
	public List<Car> getCars();
	public void setNext(Waypoint w);
}
