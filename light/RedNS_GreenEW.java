package gabriel_bouzard_myproject.light;

public class RedNS_GreenEW implements LightState {
	public RedNS_GreenEW() { }
	
	public void change(RYGLight context) {
		context.setState("RedNS_YellowEW");
	}

	public String state() {
		return "RedNS_GreenEW";
	}
}
