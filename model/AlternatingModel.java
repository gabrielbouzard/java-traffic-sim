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

class AlternatingModel implements ModelSetupStrategy {

	private ParameterSet mps = ParameterSet.getSingleton();
	
	public void setup(AnimatorBuilder builder, List<Agent> agents) {
		
		double rows = mps.get("Grid size (number of roads)").getMin();
		double columns = mps.get("Grid size (number of roads)").getMax();
		
		List<Road> hRoads = new ArrayList<Road>();
		List<Road> vRoads = new ArrayList<Road>();
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
			if (eastToWest == false) {
				Sink z = WaypointFact.newSink();
				for (int j = (int)columns; j >= 0; j--) {
					if (j == columns) {
						Road r = WaypointFact.newRoad(); 
						WaypointFact.setNext(r, z); 
						builder.addHorizontalRoad(r, i, j, eastToWest); 
						hRoads.add(r); 
						agents.add((Agent) r); 
					} else {
						WaypointFact.setNextEW(intersections[i][j], (Waypoint) hRoads.get(hRoads.size()-1));				
						Road m = WaypointFact.newRoad(); 
						m.setNext(intersections[i][j]); 
						builder.addHorizontalRoad(m, i, j, eastToWest); 
						hRoads.add(m); 
						agents.add((Agent) m);
					}
				}
				Source s = WaypointFact.newSource("WE");
				WaypointFact.setNext(s, hRoads.get(hRoads.size()-1));
				agents.add(s);
			} else {
				Source s = WaypointFact.newSource("EW");
				agents.add(s);
				for (int j = (int)columns; j >= 0; j--) {
					if (j == columns) {
						Road r = WaypointFact.newRoad(); 
						WaypointFact.setNext(s, r);
						builder.addHorizontalRoad(r, i, j, eastToWest); 
						hRoads.add(r); 
						agents.add((Agent) r); 
					} else {
						WaypointFact.setNext(hRoads.get(hRoads.size()-1), intersections[i][j]);
						Road r = WaypointFact.newRoad();
						WaypointFact.setNextEW(intersections[i][j], (Waypoint) r);
						builder.addHorizontalRoad(r, i, j, eastToWest); 
						hRoads.add(r); 
						agents.add((Agent) r); 
					}
				}
				Sink z = WaypointFact.newSink();
				WaypointFact.setNext(hRoads.get(hRoads.size()-1), z); 
			}
			eastToWest = !eastToWest;
		}
		
		/* Add Vertical Roads */
		boolean southToNorth = false;
		for (int j = 0; j < columns; j++) {
			if (!southToNorth) {
				Sink z = WaypointFact.newSink();
				for (int i = (int)rows; i >= 0; i--) {
					if (i == (int)rows) {
						Road r = WaypointFact.newRoad(); 
						WaypointFact.setNext(r, z);
						builder.addVerticalRoad(r, i, j, southToNorth);
						vRoads.add(r);
						agents.add((Agent) r);
					} else {	
						WaypointFact.setNextNS(intersections[i][j], (Waypoint) vRoads.get(vRoads.size()-1));
						Road r = WaypointFact.newRoad();
						WaypointFact.setNext(r, intersections[i][j]);
						builder.addVerticalRoad(r, i, j, southToNorth);
						vRoads.add(r);
						agents.add((Agent) r);
					}
				}	
				Source s = WaypointFact.newSource("NS");
				WaypointFact.setNext(s, vRoads.get(vRoads.size()-1));
				agents.add(s);
			} else {
				Source s = WaypointFact.newSource("SN");
				agents.add(s);
				for (int i = (int)rows; i >= 0; i--) {
					if (i == rows) {
						Road r = WaypointFact.newRoad(); 
						WaypointFact.setNext(s, r);
						builder.addVerticalRoad(r, i, j, southToNorth);
						vRoads.add(r);
						agents.add((Agent) r);
					} else {	
						WaypointFact.setNext(vRoads.get(vRoads.size() - 1), intersections[i][j]);
						Road r = WaypointFact.newRoad(); 
						WaypointFact.setNextNS(intersections[i][j], (Waypoint) r);
						builder.addVerticalRoad(r, i, j, southToNorth);
						vRoads.add(r);
						agents.add((Agent) r);
					}
				}	
				Sink z = WaypointFact.newSink();
				WaypointFact.setNext(vRoads.get(vRoads.size()-1), z);
			}
			southToNorth = !southToNorth;
		}
			
	}	
}
