package gabriel_bouzard_myproject.vehicle;

import java.awt.Color;
import java.util.LinkedList;

import gabriel_bouzard_myproject.model.swing.MP;

public class TrailingCar implements CarUpdateStrategy {
	
	private double logOfBase(double base, double num) {
	    return Math.log(num) / Math.log(base);
	}
	
	public void updatePosition(Car car, LinkedList<Vehicle> vehicles) {
		if (vehicles.isEmpty()) { return; }
		try {
			Vehicle front = vehicles.get(vehicles.indexOf(car) - 1);
			
			double multiplier = logOfBase(car.length(), 8);
			if (multiplier <= 1) {
				multiplier = 1;
			}
			if (car.position() >= (front.position() - (Math.pow(MP.carLength, multiplier) * 2))) {
				if (front.getColor().equals(new Color(0, 255, 0))) {
					car.changeColor(255, 255, 0);
				} else if (front.getColor().equals(new Color(255, 0, 0))){
					car.changeColor(255, 0, 0);
				} 
				car.changePosition(0.0);
				return;
			} else {
 			    car.changeColor(0, 255, 0);
 			    car.changePosition(1.0);
 			    return;
			}
		} catch (IndexOutOfBoundsException e) { }
		
	}
}
