package gabriel_bouzard_myproject.waypoint;

import gabriel_bouzard_myproject.cmd.Command;
import gabriel_bouzard_myproject.light.RYGLight;

public class WaypointFact {
	
	static public OneWay newRoad() {
		return new OneWay();
	}	
	
	static public Source newSource(String direction) {
		return new Source(direction);
	}
	
	static public Intersection newIntersection() {
		return new Intersection();
	}
	
	static public Sink newSink() {
		return new Sink();
	}
	
	static public void setNextEW(Intersection i, Waypoint w) {
		i.nextHorizontal(w); 
	}
	
	static public void setNextNS(Intersection i, Waypoint w) {
		i.nextVerticle(w);
	}
	
	static public void setNext(Source s, Road road) {
		s.setNext((Waypoint) road);
	}
	
	static public void setNext(Road r, Sink z) {
		r.setNext(z);
	}
	
	static public void setNext(Waypoint x, Waypoint y) {
		if (x == null || y == null) throw new IllegalArgumentException();
		x.setNext(y);
	}

	public static void setNext(Road road, Intersection intersection) {
		road.setNext(intersection);
	}

}
