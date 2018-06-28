package gabriel_bouzard_myproject.vehicle;

import java.util.LinkedList;

import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.model.swing.MP;

public class LeadCar implements CarUpdateStrategy {

	public void updatePosition(Car car, LinkedList<Vehicle> vehicles) {
		ParameterSet mps = ParameterSet.getSingleton();
		double breakPoint = MP.roadLength - car.length() - car.breakDistance();
		double stopPoint = MP.roadLength - car.length() - car.stopDistance();
		
		if ((car.getLight().equals("RedNS_GreenEW")) ||
			    (car.getLight().equals("RedNS_YellowEW") && (car.position() < breakPoint)) ||
			    (car.getLight().equals("RedNS_YellowEW") && (stopPoint < car.position())) ||
			    ((car.getLight().equals("YellowNS_RedEW") || car.getLight().equals("GreenNS_RedEW")) && (car.position() < breakPoint))) {
			    car.changeColor(0, 255, 0);
			    car.changePosition(1.0);
		} else if (((car.getLight().equals("RedNS_YellowEW")) && (breakPoint < car.position()) && (car.position() <= (stopPoint)))) {
				car.changeColor(255, 255, 0);
				car.changePosition(0.25);
		} else {
				car.changeColor(255, 0, 0);
				car.changePosition(0.0);
		} 

	}
}
 
