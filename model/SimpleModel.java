package gabriel_bouzard_myproject.model;

import java.util.ArrayList;
import java.util.List;

import gabriel_bouzard_myproject.cmd.CmdFact;
import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.waypoint.Intersection;
import gabriel_bouzard_myproject.waypoint.Road;
import gabriel_bouzard_myproject.waypoint.Sink;
import gabriel_bouzard_myproject.waypoint.Source;
import gabriel_bouzard_myproject.waypoint.Waypoint;
import gabriel_bouzard_myproject.waypoint.WaypointFact;

public class SimpleModel implements ModelSetupStrategy {
	
	private ParameterSet mps = ParameterSet.getSingleton();
	
	public void setup(AnimatorBuilder builder, List<Agent> agents) {
		
		double rows = mps.get("Grid size (number of roads)").getMin();
		double columns = mps.get("Grid size (number of roads)").getMax();
		
		List<Road> roads = new ArrayList<Road>();
		List<Road> vroads = new ArrayList<Road>();
		Intersection[][] intersections = new Intersection[(int)rows][(int)columns];
	  
		/* Add intersections */
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				intersections[i][j] = WaypointFact.newIntersection();
				builder.addIntersection(intersections[i][j], i, j);
	            builder.addLight(intersections[i][j].nextLight(), i, j);
	            agents.add(intersections[i][j]);
	            agents.add(intersections[i][j].nextLight());
			}
		}
			
		/* Add Horizontal Roads */
		boolean eastToWest = false;
		for (int i = 0; i < rows; i++) {
			Sink z = WaypointFact.newSink();
			for (int j = (int)columns; j >= 0; j--) {
				if (j == columns) {
					Road r = WaypointFact.newRoad(); 
					WaypointFact.setNext((Waypoint) r, z);
					builder.addHorizontalRoad(r, i, j, eastToWest); 
					roads.add(r);
					agents.add((Agent) r); 
				} else {
					WaypointFact.setNextEW(intersections[i][j], (Waypoint) roads.get(roads.size() - 1));
					Road r = WaypointFact.newRoad(); 
					WaypointFact.setNext((Waypoint) r, intersections[i][j]);
					builder.addHorizontalRoad(r, i, j, eastToWest);
					roads.add(r); 
					agents.add((Agent) r); 
				}
			}
			Source s = WaypointFact.newSource("WE");
			WaypointFact.setNext(s, roads.get(roads.size()-1));
			agents.add(s);
		}
		
		/* Add Vertical Roads */
		boolean southToNorth = false;
		for (int j = 0; j < (int)columns; j++) {
        	Sink z = WaypointFact.newSink();
			for (int i = (int)rows; i >= 0; i--) {
				if (i == rows) {
					Road r = WaypointFact.newRoad(); // create a new road
					WaypointFact.setNext((Waypoint) r, z);
					builder.addVerticalRoad(r, i, j, southToNorth); // display the road using UI
					vroads.add(r); // add the road to list of horizontal roads
					agents.add((Agent) r); // add the new road to the list of agents
				} else {
					WaypointFact.setNextNS(intersections[i][j], (Waypoint) vroads.get(vroads.size()-1));
					Road r = WaypointFact.newRoad();				
					WaypointFact.setNext((Waypoint) r, intersections[i][j]);
					builder.addVerticalRoad(r, i, j, southToNorth); // display the road using UI
					vroads.add(r); // add the road to list of roads
					agents.add((Agent) r);
				}
			}
			Source s = WaypointFact.newSource("NS");
			WaypointFact.setNext(s, vroads.get(vroads.size()-1));
			agents.add(s);
		} 
	}	
}
