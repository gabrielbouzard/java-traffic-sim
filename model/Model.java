package gabriel_bouzard_myproject.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.util.Animator;

/**
 * An example to model for a simple visualization.
 * The model contains two roads.
 * See {@link #SimpleModel(AnimatorBuilder)}.
 */
public class Model extends Observable {
	protected List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time;
	ParameterSet mps = ParameterSet.getSingleton();

	/** Creates a model to be visualized using the <code>builder</code>.
	 *  If the builder is null, no visualization is performed.
	 *  Each road has one {@link Car}.
	 *
	 */
	public Model(AnimatorBuilder builder) {
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		agents = new ArrayList<Agent>();
		setup(builder);
		animator = builder.getAnimator();
		super.addObserver(animator);
	}

	/**
	 * Run the simulation for <code>duration</code> model seconds.
	 */
	public void run(int duration) {
		if (disposed)
			throw new IllegalStateException();
		for (int i=0; i<duration; i++) {
			time++;
			Agent[] agents_copy = agents.toArray(new Agent[0]);
			for (Agent a : agents_copy) {
				a.run(time);
			}
			super.setChanged();
			super.notifyObservers();
		}
	}

	/**
	 * Throw away this model.
	 */
	public void dispose() {
		animator.dispose();
		disposed = true;
	}

	public void setup(AnimatorBuilder builder) { 
		if (mps.get("Traffic pattern").getMin() == 0) {
			new SimpleModel().setup(builder, agents);
		} else {
			new AlternatingModel().setup(builder, agents);
		}
	}

}
