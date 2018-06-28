package gabriel_bouzard_myproject.main;

import javax.swing.Box;
import javax.swing.JOptionPane;

import gabriel_bouzard_myproject.cmd.Command;
import gabriel_bouzard_myproject.cmd.CmdFact;
import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.model.Model;
import gabriel_bouzard_myproject.model.swing.SwingAnimatorBuilder;
import gabriel_bouzard_myproject.ui.*;

/**
 * A class to build UIMenus and UIForms and attach them to a user interface.
 * @param UI ui
 */
class Control {
	private static final int EXITED = 0; 
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int NUMSTATES = 3;
	private UIMenu[] menus; 
	private int state; 

	private UIForm minMaxForm;
	private UIFormTest numberTest;
	private UIFormTest stringTest;

	private ParameterSet modelParameters;
	private UI ui;
		
	Control(UI ui) {
		this.modelParameters = ParameterSet.getSingleton();
		this.ui = ui;
		
		menus = new UIMenu[NUMSTATES];
		state = START;
		addSTART(START);
		addEXIT(EXIT);
		
		stringTest = input -> ! "".equals(input.trim());
		
		numberTest = input -> {
			try {
				Double.parseDouble(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};
		
	}
	
	void run() { 
		try {
			while (state != EXITED) {
				ui.processMenu(menus[state]);
			}
		} catch (UIError e) { 
			ui.displayError("UI closed");
		}
	}
	
	private void addSTART(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();
	
		m.add("", 
				() -> {
					addEXIT(EXIT);
				});
		
		m.add("Run Simulation", 
				() -> {
					Command c = CmdFact.newRunSimCmd();
					c.run();
					state = EXITED;
				});
		
		m.add("Change Simulation Parameters",
				() -> {
					addCHANGE(START);
				});
		
		m.add("Exit",
				() -> state = EXIT);
		
		menus[stateNum] = m.toUIMenu("Traffic Simulation");
	}
	
	
	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();
		m.add("Default",
				() -> ui.displayError("Not a valid answer!"));
		m.add("Yes", 
				() -> state = EXITED);
		m.add("No", 
				() -> state = START);
		menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}
	
	private void addCHANGE(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();
		
		m.add("", 
			() -> {
				addSTART(START);
			});
		m.add("Show current values (seconds)",
			() -> {
				ui.displayMessage(modelParameters.toString());
			});
		m.add("Simulation time step (seconds)", 
			() -> {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Enter simulation time step: ", numberTest);
				String[] response = ui.processForm(f.toUIForm(""));
				
				Double selection;
				try {
					selection = Double.parseDouble(response[0]);
					if (selection < 0 || selection == null) selection = 0.1;
				} catch (NumberFormatException e) {
					selection = 0.1;
				}
				
				Command cmd = CmdFact.newChangeTimeStepCmd(selection);
				cmd.run();
			});
		m.add("Simulation run time (seconds)",
			() -> {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Enter simulation run time: ", numberTest);
				String[] response = ui.processForm(f.toUIForm(""));
				
				Double selection;
				try {
					selection = Double.parseDouble(response[0]);
					if (selection < 0 || selection == null) selection = 1000.0;
				} catch (NumberFormatException e) {
					selection = 1000.0;
				}
				
				Command cmd = CmdFact.newChangeRunTimeCmd(selection);
				cmd.run();
			});
		m.add("Grid size (number of roads)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter number of rows: ", numberTest);
					f.add("Enter number of columns: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 2.0;
						if (selection2 < 0) selection2 = 3.0;
					} catch (NumberFormatException e) {
						selection1 = 2.0;
						selection2 = 3.0;
					}
					
					Command cmd = CmdFact.newChangeGridSizeCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Traffic pattern", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter traffic pattern (options are 'simple' or 'alternating'): ", stringTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Command cmd = CmdFact.newChangeTrafficPatternCmd(response[0]);
					cmd.run();
				});
		m.add("Car entry rate (seconds/car)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimum car entry rate: ", numberTest);
					f.add("Enter maximum car entry rate: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 2.0;
						if (selection2 < 0) selection2 = 25.0;
					} catch (NumberFormatException e) {
						selection1 = 2.0;
						selection2 = 25.0;
					}
					
					Command cmd = CmdFact.newChangeCarEntryRateCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Road segment length (meters)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimum road length: ", numberTest);
					f.add("Enter maximum road length: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 200.0;
						if (selection2 < 0) selection2 = 500.0;
					} catch (NumberFormatException e) {
						selection1 = 200.0;
						selection2 = 500.0;
					}
					
					Command cmd = CmdFact.newChangeRoadLengthCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Intersection length (meters)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimum intersection length: ", numberTest);
					f.add("Enter maximum intersection length: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 10.0;
						if (selection2 < 0) selection2 = 15.0;
					} catch (NumberFormatException e) {
						selection1 = 10.0;
						selection2 = 15.0;
					}
					
					Command cmd = CmdFact.newChangeIntersectionLengthCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Car length (meters)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimum car length: ", numberTest);
					f.add("Enter maximum car length: ", numberTest);
					String[] response = ui.processForm(f.toUIForm("hess"));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 5.0;
						if (selection2 < 0) selection2 = 10.0;
					} catch (NumberFormatException e) {
						selection1 = 5.0;
						selection2 = 10.0;
					}
					
					Command cmd = CmdFact.newChangeCarLengthCmd(selection1, selection2);
					//}	cmd.run();
				});
		m.add("Car maximum velocity (meters/second)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimum car max-velocity: ", numberTest);
					f.add("Enter maximum car max-velocity: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 10.0;
						if (selection2 < 0) selection2 = 30.0;
					} catch (NumberFormatException e) {
						selection1 = 10.0;
						selection2 = 30.0;
					}
					
					Command cmd = CmdFact.newChangeCarMaximumVelocityCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Car stop distance (meters)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Ent				er minimum car stop distance: ", numberTest);
					f.add("Enter maximum car stop distance: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 0.5;
						if (selection2 < 0) selection2 = 5.0;
					} catch (NumberFormatException e) {
						selection1 = 0.5;
						selection2 = 5.0;
					}
					
					Command cmd = CmdFact.newChangeCarStopDistanceCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Car break distance (meters)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimum car break distance: ", numberTest);
					f.add("Enter maximum car break distance: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 9.0;
						if (selection2 < 0) selection2 = 10.0;
					} catch (NumberFormatException e) {
						selection1 = 9.0;
						selection2 = 10.0;
					}
					
					Command cmd = CmdFact.newChangeCarBreakDistanceCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Traffic light green time (seconds)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimum traffic light green time: ", numberTest);
					f.add("Enter maximum traffic light green time: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 30.0;
						if (selection2 < 0) selection2 = 180.0;
					} catch (NumberFormatException e) {
						selection1 = 30.0;
						selection2 = 180.0;
					}
					
					Command cmd = CmdFact.newChangeTrafficLightGreenTimeCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Traffic light yellow time (seconds)", 
				() -> {
					UIFormBuilder f = new UIFormBuilder();
					f.add("Enter minimu-1m traffic light yellow time: ", numberTest);
					f.add("Enter maximum traffic light yellow time: ", numberTest);
					String[] response = ui.processForm(f.toUIForm(""));
					
					Double selection1;
					Double selection2;
					try {
						selection1 = Double.parseDouble(response[0]);
						selection2 = Double.parseDouble(response[1]);
						if (selection1 < 0) selection1 = 4.0;
						if (selection2 < 0) selection2 = 5.0;
					} catch (NumberFormatException e) {
						selection1 = 4.0;
						selection2 = 5.0;
					}
					
					Command cmd = CmdFact.newChangeTrafficLightYellowTimeCmd(selection1, selection2);
					cmd.run();
				});
		m.add("Reset simulation and return to the main menu", 
				() -> {
					addSTART(START);
				});	
		menus[stateNum] = m.toUIMenu("Modify Simulation Parameters:\n");
	}
	
}
