package gabriel_bouzard_myproject.vehicle;

import java.util.LinkedList;

public interface CarUpdateStrategy {
	public void updatePosition(Car car, LinkedList<Vehicle> vehicles);
}
