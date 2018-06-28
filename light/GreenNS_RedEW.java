package gabriel_bouzard_myproject.light;

public class GreenNS_RedEW implements LightState {
	
	public GreenNS_RedEW() { }
	
	public void change(RYGLight context) {
		context.setState("YellowNS_RedEW");
	}
	
	public String state() {
		return "GreenNS_RedEW";
	}
	
}
