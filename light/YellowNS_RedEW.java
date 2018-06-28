package gabriel_bouzard_myproject.light;

public class YellowNS_RedEW implements LightState {
	
	public YellowNS_RedEW() { }
	
	public void change(RYGLight context) {
		context.setState("RedNS_GreenEW");
	}

	public String state() {
		return "YellowNS_RedEW";
	}
}

