package gabriel_bouzard_myproject.light;

import gabriel_bouzard_myproject.model.Agent;
import gabriel_bouzard_myproject.vehicle.Vehicle;

public interface Light extends Agent {
	public String state();
	public void setState(String state);
	public void addObserver(Vehicle v);
	public void removeObserver(Vehicle v);
}
