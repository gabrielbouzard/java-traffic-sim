package gabriel_bouzard_myproject.light;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.model.Agent;
import gabriel_bouzard_myproject.vehicle.Car;
import gabriel_bouzard_myproject.vehicle.Vehicle;

/**
 * A light has one of four states: GreenNS/RedEW, YellowNS/RedEW, RedNS/GreenEW, RedNS/YellowEW.
 */
public class RYGLight extends Observable implements Light {
	ParameterSet mps = ParameterSet.getSingleton();
	private List<Vehicle> observers = new ArrayList<Vehicle>();
	private LightState state = new RedNS_GreenEW(); // the observable, watched value
	/** Duration of light values */
	private double greenDurationNS;
	private double yellowDurationNS;
	private double greenDurationEW;
	private double yellowDurationEW;
	
	RYGLight() { 
	
		this.greenDurationNS = mps.get("Traffic light green time (seconds)").getMin() + 
            Math.round((Math.random() * 
            ((mps.get("Traffic light green time (seconds)").getMax() - mps.get("Traffic light green time (seconds)").getMin()) + 1.0)));
	
		this.yellowDurationNS = mps.get("Traffic light yellow time (seconds)").getMin() + 
            Math.round((Math.random() * 
            ((mps.get("Traffic light yellow time (seconds)").getMax() - mps.get("Traffic light yellow time (seconds)").getMin()) + 1.0)));
	
		this.greenDurationEW = mps.get("Traffic light green time (seconds)").getMin() + 
            Math.round((Math.random() * 
            ((mps.get("Traffic light green time (seconds)").getMax() - mps.get("Traffic light green time (seconds)").getMin()) + 1.0)));
	
		this.yellowDurationEW = mps.get("Traffic light yellow time (seconds)").getMin() + 
            Math.round((Math.random() * 
            ((mps.get("Traffic light yellow time (seconds)").getMax() - mps.get("Traffic light yellow time (seconds)").getMin()) + 1.0)));
	
	}
	
	public void run(double time) {
		if (state.state().equals("RedNS_GreenEW") && ((int)(time % greenDurationEW) == 0)) {
			state.change(this);
		} else if (state.state().equals("RedNS_YellowEW") && ((int)(time % yellowDurationEW) == 0)) {
			state.change(this);
		} else if (state.state().equals("GreenNS_RedEW") && ((int)(time % greenDurationNS) == 0)) {
			state.change(this);
		} else if (state.state().equals("YellowNS_RedEW") && ((int)(time % yellowDurationNS) == 0)) {
			state.change(this);
		}
	}
	
	public void setState(String newState) throws IllegalArgumentException {
		if (!state.state().equals(newState)) {
			if (newState.equals("RedNS_GreenEW")) this.state = new RedNS_GreenEW();
			else if (newState.equals("RedNS_YellowEW")) this.state = new RedNS_YellowEW();
			else if (newState.equals("GreenNS_RedEW")) this.state = new GreenNS_RedEW();
			else if (newState.equals("YellowNS_RedEW")) this.state = new YellowNS_RedEW();
		}
		setChanged();
		notifyObservers(newState);
	}
	
	public String state() {
		return this.state.state();
	}
	
	public void addObserver(Vehicle v) {
		observers.add(v);
	}
	
	public void removeObserver(Vehicle v) {
		observers.remove(v);
	}
}
