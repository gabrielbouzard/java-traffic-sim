package gabriel_bouzard_myproject.light;

public class RedNS_YellowEW implements LightState {
	public RedNS_YellowEW() { }
	
	public void change(RYGLight context) {
		context.setState("GreenNS_RedEW");
	}
	
	public String state() {
		return "RedNS_YellowEW";
	}
}
