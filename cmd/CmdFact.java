package gabriel_bouzard_myproject.cmd;

import gabriel_bouzard_myproject.cmd.*;

public class CmdFact {	
	private CmdFact() { } 
		
	static public Command newChangeCarBreakDistanceCmd(Double min, Double max) {
		return new ChangeCarBreakDistanceCmd(min, max);
	}
	
	static public Command newChangeCarEntryRateCmd(Double min, Double max) {
		return new ChangeCarEntryRateCmd(min, max);
	}
	
	static public Command newChangeCarLengthCmd(Double min, Double max) {
		return new ChangeCarLengthCmd(min, max);
	}
	
	static public Command newChangeCarMaximumVelocityCmd(Double min, Double max) {
		return new ChangeCarMaximumVelocityCmd(min, max);
	}
	
	static public Command newChangeCarStopDistanceCmd(Double min, Double max) {
		return new ChangeCarStopDistanceCmd(min, max);
	}
	
	static public Command newChangeGridSizeCmd(Double rows, Double columns) {
		return new ChangeGridSizeCmd(rows, columns);
	}
	
	static public Command newChangeIntersectionLengthCmd(Double min, Double max) {
		return new ChangeIntersectionLengthCmd(min, max);
	}
	
	static public Command newChangeRoadLengthCmd(Double min, Double max) {
		return new ChangeRoadLengthCmd(min, max);
	}
	
	static public Command newChangeRunTimeCmd(Double time) {
		return new ChangeRunTimeCmd(time);
	}
	
	static public Command newChangeTimeStepCmd(Double step) {
		return new ChangeTimeTimeStepCmd(step);
	}
	
	static public Command newChangeTrafficLightGreenTimeCmd(Double min, Double max) {
		return new ChangeTrafficLightGreenTimeCmd(min, max);
	}
						  
	static public Command newChangeTrafficLightYellowTimeCmd(Double min, Double max) {
		return new ChangeTrafficLightYellowTimeCmd(min, max);
	}
	
	static public Command newChangeTrafficPatternCmd(String s) {
		double pattern;
		if (s.equals("simple")) pattern = 0;
		else pattern = 1;
		return new ChangeTrafficPatternCmd(pattern);
	}
	
	static public Command newRunSimCmd() {
		return new RunSimCmd();
	}
	
}
