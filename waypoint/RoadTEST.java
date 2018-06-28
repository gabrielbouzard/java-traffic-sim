package gabriel_bouzard_myproject.waypoint;

import static org.junit.Assert.*;
import org.junit.Test;

public class RoadTEST {
	
	OneWay r1 = WaypointFact.newRoad();
	OneWay r2 = WaypointFact.newRoad();
	
	@Test
	public void testConstructorAndAttributes() {

		assertNotEquals(r1.length(), r2.length());
		assertSame(r1.full(), r2.full());
		Waypoint w = null;
		try {
			WaypointFact.setNext(r1, w);
			fail();
		} catch (IllegalArgumentException e) { }
	}

}
