package gabriel_bouzard_myproject.waypoint;

import java.util.List;
import java.util.Queue;

import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.light.Light;
import gabriel_bouzard_myproject.light.RYGLight;
import gabriel_bouzard_myproject.model.Agent;
import gabriel_bouzard_myproject.model.swing.MP;
import gabriel_bouzard_myproject.vehicle.Car;
import gabriel_bouzard_myproject.vehicle.LeadCar;
import gabriel_bouzard_myproject.vehicle.TrailingCar;
import gabriel_bouzard_myproject.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A road holds cars.
 */
class OneWay implements Agent, Waypoint, Road {

	private Queue<Vehicle> vehicles = new LinkedList<Vehicle>();
	private Waypoint next;
	private Waypoint thisWaypoint;

	private final double roadSegmentLength;
	
	private ParameterSet mps = ParameterSet.getSingleton();
	
	OneWay() { 
		roadSegmentLength = mps.get("Road segment length (meters)").getMin() + 
                Math.round((Math.random() * 
                ((mps.get("Road segment length (meters)").getMax() - mps.get("Road segment length (meters)").getMin()) + 1.0)));
	} 
	
	public boolean accept(Vehicle v) {
		if (v == null) { throw new IllegalArgumentException(); }
		if (full()) return false;
		v.setWaypoint(this);
		try {
			nextLight().addObserver(v); 
		} catch (NullPointerException e) { }
		v.resetPosition();		
		vehicles.add(v);
		return true;		
	}
	
	/*public Waypoint getNext() {
		return next;
	}*/

	public Double length() {
		return roadSegmentLength;
	}
	
	public Light nextLight() {
		return next.nextLight();
	}
	
	public boolean full() {
		if ((vehicles.size() * (MP.carLength * 1.5)) >= roadSegmentLength) {
			return true;
		} else {
			return false;
		}
	}

	public void run(double time) {
		if (vehicles.isEmpty()) { return; }
		if (vehicles.peek().position() >= (MP.roadLength - vehicles.peek().length() - vehicles.peek().stopDistance() - 10)) {
			if (next.accept(vehicles.peek())) {
				vehicles.remove();
			} else {
				vehicles.peek().updatePosition(new LeadCar(), (LinkedList<Vehicle>)vehicles);
			}
			if (vehicles.isEmpty()) { return; }
		} 
		vehicles.peek().updatePosition(new LeadCar(), (LinkedList<Vehicle>)vehicles);
		for (Vehicle v : vehicles) {
			if (v.equals(vehicles.peek())) {
				continue;
			}
			v.updatePosition(new TrailingCar(), (LinkedList<Vehicle>)vehicles); 
		}
	}
	
	public void setNext(Waypoint waypoint) {
		this.next = waypoint;
	}
	
	private List<Vehicle> getVehicles() {  
		return new ArrayList<Vehicle>(vehicles);
	}
	
	public List<Car> getCars() {
		List<Car> cars = new ArrayList<Car>();
		for (Vehicle v : vehicles) {
			if (v instanceof Car) {
				cars.add((Car)v);
			}
		}
		return cars;
	}
	
	
}
