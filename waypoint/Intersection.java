package gabriel_bouzard_myproject.waypoint;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

import gabriel_bouzard_myproject.cmd.CmdFact;
import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.light.Light;
import gabriel_bouzard_myproject.light.LightFact;
import gabriel_bouzard_myproject.light.RYGLight;
import gabriel_bouzard_myproject.model.Agent;
import gabriel_bouzard_myproject.vehicle.LeadCar;
import gabriel_bouzard_myproject.vehicle.TrailingCar;
import gabriel_bouzard_myproject.vehicle.Vehicle;

public class Intersection implements Waypoint, Agent {	
	
	private Waypoint nextVerticle;
	private Waypoint nextHorizontal;
	private final double intersectionLength;

	private Queue<Vehicle> vehicles = new LinkedList<Vehicle>();
	private Waypoint next;
	private RYGLight light;
	
	private ParameterSet mps = ParameterSet.getSingleton();
	
	Intersection() { 
		this.intersectionLength = mps.get("Intersection length (meters)").getMin() + 
				Math.round((Math.random() * 
						((mps.get("Intersection length (meters)").getMax() - mps.get("Intersection length (meters)").getMin()) + 1.0)));
		this.light = LightFact.newRYGLight();
	}
	
	public void run(double time) {
		if (acceptingHorizontal()) {
			next = nextHorizontal;
		} else {
			next = nextVerticle;
		}
		
		if (vehicles.isEmpty()) { return; } 
		if (vehicles.peek().position() >= (intersectionLength - (vehicles.peek().length() * 1.5))) { // MP.intersectionLength
			if (vehicles.isEmpty()) { return; } 
			if (next.accept(vehicles.peek())) {
				vehicles.remove();
			}
			if (vehicles.isEmpty()) { return; } 
		} else {
			vehicles.peek().updatePosition(new LeadCar(), (LinkedList<Vehicle>)vehicles);
			for (Vehicle car : vehicles) {
				if (car.equals(vehicles.peek())) {
					continue;
				}
				car.updatePosition(new TrailingCar(), (LinkedList<Vehicle>)vehicles); 
			}
		}
	}

	public boolean accept(Vehicle car) {
		if (car == null) { throw new IllegalArgumentException(); }
		if (full()) return false;
		if (acceptingHorizontal() && car.isHorizontal()) {
			light.removeObserver(car);
			car.setWaypoint(this);
			vehicles.add(car);
			return true;
		} else if (acceptingVerticle() && !car.isHorizontal()){
			light.removeObserver(car);
			car.setWaypoint(this);
			vehicles.add(car);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean acceptingHorizontal() {
		if ((light.state().equals("RedNS_GreenEW") || light.state().equals("RedNS_YellowEW"))) {
			if (vehicles.peek() == null) return true; 
			else if (vehicles.peek().isHorizontal()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/* redundant method used simply for clarity */
	private boolean acceptingVerticle() {
		if (!acceptingHorizontal()) return true;
		else return false;
	}

	public Waypoint getNext() {
		return next;
	}
	
	public Light nextLight() {
		return light;
	}
	
	public boolean full() {
		return ((vehicles.size() * (mps.get("Car length (meters)").getMax() * 2.5)) >= mps.get("Intersection length (meters)").getMin());
	}

	public Double length() {
		return intersectionLength;
	}
	
	void nextHorizontal(Waypoint nextHorizontal) {
		this.nextHorizontal = nextHorizontal;
	}
	
	void nextVerticle(Waypoint nextVerticle) {
		this.nextVerticle = nextVerticle;
	}

	public void setNext(Waypoint w) {
		this.next = w;		
	}

}
