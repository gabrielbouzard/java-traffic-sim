package gabriel_bouzard_myproject.model;

import java.util.List;

public interface ModelSetupStrategy {
	public void setup(AnimatorBuilder builder, List<Agent> agents);
}
