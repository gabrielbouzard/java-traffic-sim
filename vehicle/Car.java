package gabriel_bouzard_myproject.vehicle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.model.swing.MP;
import gabriel_bouzard_myproject.waypoint.Waypoint;

/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern.
 */
public class Car implements Observer, Vehicle {
	ParameterSet mps; 
	private double carLength;
	private double carMaxVelocity;
	private double carStopDistance;
	private double carBreakDistance;
	private double velocity;
	private double position = 0;
	private java.awt.Color color = new Color(0, 255, 0);
	private Waypoint currentWaypoint;
	private boolean horizontal;
	private boolean alternate;
	private String light = "GreenNS_RedEW";
	
	Car() {
		this.mps = ParameterSet.getSingleton();
		this.carLength = mps.get("Car length (meters)").getMin() + 
				Math.round((Math.random() * (
						(mps.get("Car length (meters)").getMax() - mps.get("Car length (meters)").getMin() + 1.0))));
		this.carMaxVelocity = mps.get("Car maximum velocity (meters/second)").getMin() +
				Math.round((Math.random() * (
						(mps.get("Car maximum velocity (meters/second)").getMax() - mps.get("Car maximum velocity (meters/second)").getMin() + 1.0))));
		this.carStopDistance = mps.get("Car stop distance (meters)").getMin() +
				Math.round((Math.random() * (
						(mps.get("Car stop distance (meters)").getMax() - mps.get("Car stop distance (meters)").getMin() + 1.0))));
		this.carBreakDistance = mps.get("Car brake distance (meters)").getMin() +
				Math.round((Math.random() * (
						(mps.get("Car brake distance (meters)").getMax() - mps.get("Car brake distance (meters)").getMin() + 1.0))));
		this.velocity = mps.get("Car maximum velocity (meters/second)").getMin() +
				Math.round((Math.random() * (
						(mps.get("Car maximum velocity (meters/second)").getMax() - mps.get("Car maximum velocity (meters/second)").getMin() + 1.0))));
	}
	
	public double position() {
		return position;
	}
	
	public void changeColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}
	
	public double length() {
		return carLength;
	}
	
	public Waypoint waypoint() {
		return currentWaypoint;
	}
	
	public void setWaypoint(Waypoint w) {
		currentWaypoint = w;
	}
	
	public java.awt.Color getColor() {
		return color;
	}
	
	public double stopDistance() {
		return carStopDistance;
	}
	
	public double breakDistance() {
		return carBreakDistance;
	}
	
	public void resetPosition() {
		position = 0;
	}
	
	public String getLight() {
		return light;
	}
	
	public void horizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
	
	public boolean isAlternate() {
		return this.alternate;
	}
	
	public void alternate(boolean direction) {
		this.alternate = direction;
	}
	
	public void changePosition(double c) {
		double i = ((currentWaypoint.length() * c) / MP.roadLength);
		position += (velocity * i);
	}
	
	public void updatePosition(CarUpdateStrategy strat, LinkedList<Vehicle> vehicles) {
		strat.updatePosition(this, vehicles);
	}

	public void update(Observable obj, Object arg) {
		light = (String)arg;
	}

}
