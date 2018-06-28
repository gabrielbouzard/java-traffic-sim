package gabriel_bouzard_myproject.waypoint;

import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.light.RYGLight;
import gabriel_bouzard_myproject.model.Agent;
import gabriel_bouzard_myproject.vehicle.Car;
import gabriel_bouzard_myproject.vehicle.VehicleFact;

/*
 * Source - a Car factory
 */
public class Source implements Agent {
	
	private boolean horizontal; // true if east-west or west-east source, false otherwise
	private boolean alternate;
	private Waypoint next;
	private double delay;
	
	ParameterSet mps = ParameterSet.getSingleton();
	
	Source(String direction) throws IllegalArgumentException { 
		
		if (direction.equals("NS")) {
			this.alternate = false;
			this.horizontal = false;
		} else if (direction.equals("SN")) {
			this.alternate = true;
			this.horizontal = false;
		} else if (direction.equals("WE")) {
			this.alternate = false;
			this.horizontal = true;
		} else if (direction.equals("EW")) {
			this.alternate = true;
			this.horizontal = true;
		} else {
			throw new IllegalArgumentException();
		}
		
		this.delay = mps.get("Car entry rate (seconds/car)").getMin() + 
	            Math.round((Math.random() * 
	                    ((mps.get("Car entry rate (seconds/car)").getMax() - mps.get("Car entry rate (seconds/car)").getMin()) + 1.0)));
	} 
	
	Source(String direction, Waypoint next) { 
		
		if (direction.equals("NS")) {
			this.alternate = false;
			this.horizontal = false;
		} else if (direction.equals("SN")) {
			this.alternate = true;
			this.horizontal = false;
		} else if (direction.equals("WE")) {
			this.alternate = false;
			this.horizontal = true;
		} else if (direction.equals("EW")) {
			this.alternate = true;
			this.horizontal = true;
		} else {
			throw new IllegalArgumentException();
		}
		
		this.next = next;
		this.delay = mps.get("Car entry rate (seconds/car)").getMin() + 
	            Math.round((Math.random() * 
	                    ((mps.get("Car entry rate (seconds/car)").getMax() - mps.get("Car entry rate (seconds/car)").getMin()) + 1.0)));
	} 

	public void run(double time) {
		if (time % delay == 0) {
			buildCar();
		}
	}

	private void buildCar() {
		Car car = VehicleFact.newCar();
		car.horizontal(horizontal);
		car.alternate(alternate);
		next.accept(car);
	}
	
	void setNext(Waypoint w) {
		this.next = w;
	}	
	
}
