package gabriel_bouzard_myproject.model.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import gabriel_bouzard_myproject.cmd.ParameterSet;
import gabriel_bouzard_myproject.light.Light;
import gabriel_bouzard_myproject.model.AnimatorBuilder;
import gabriel_bouzard_myproject.util.Animator;
import gabriel_bouzard_myproject.util.SwingAnimator;
import gabriel_bouzard_myproject.util.SwingAnimatorPainter;
import gabriel_bouzard_myproject.vehicle.Car;
import gabriel_bouzard_myproject.waypoint.Intersection;
import gabriel_bouzard_myproject.waypoint.Road;
import gabriel_bouzard_myproject.waypoint.Sink;
import gabriel_bouzard_myproject.waypoint.Source;

/**
 * A class for building Animators.
 */
public class SwingAnimatorBuilder implements AnimatorBuilder {
	MyPainter painter;
	ParameterSet mps = ParameterSet.getSingleton();
	
	public SwingAnimatorBuilder() {
		painter = new MyPainter();
		//modelParameters = (ModelParameterSet)mps;
	}
	
	public Animator getAnimator() {
		if (painter == null) { throw new IllegalStateException(); }
		Animator returnValue = new SwingAnimator(painter, "Traffic Simulator",
				VP.displayWidth, VP.displayHeight, VP.displayDelay);
		painter = null;
		return returnValue;
	}
	
	private static double skipInit = VP.gap;
	private static double skipRoad = (VP.gap + MP.roadLength);
	private static double skipCar = VP.gap + VP.elementWidth;
	private static double skipRoadCar = skipRoad + skipCar;
	public void addLight(Light d, int i, int j) {
		double x = skipInit + skipRoad + j*skipRoadCar;
		double y = skipInit + skipRoad + i*skipRoadCar;
		Translator t = new TranslatorWE(x, y, MP.carLength, VP.elementWidth, VP.scaleFactor);
		painter.addLight(d,t);
	}
	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
		double x = skipInit + j*skipRoadCar;
		double y = skipInit + skipRoad + i*skipRoadCar;
		Translator t = eastToWest ? new TranslatorEW(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
				: new TranslatorWE(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor);
		painter.addRoad(l,t);
	}
	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
		double x = skipInit + skipRoad + j*skipRoadCar;
		double y = skipInit + i*skipRoadCar;
		Translator t = southToNorth ? new TranslatorSN(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
				: new TranslatorNS(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor);
		painter.addRoad(l,t);
	}


	/** Class for drawing the Model. */
	private static class MyPainter implements SwingAnimatorPainter {

		/** Pair of a model element <code>x</code> and a translator <code>t</code>. */
		private static class Element<T> {
			T x;
			Translator t;
			Element(T x, Translator t) {
				this.x = x;
				this.t = t;
			}
		}

		private List<Element<Road>> roadElements;
		private List<Element<Light>> lightElements;
		MyPainter() {
			roadElements = new ArrayList<Element<Road>>();
			lightElements = new ArrayList<Element<Light>>();
		}
		void addLight(Light x, Translator t) {
			lightElements.add(new Element<Light>(x,t));
		}
		void addRoad(Road x, Translator t) {
			roadElements.add(new Element<Road>(x,t));
		}

		public void paint(Graphics g) {
			// This method is called by the swing thread, so may be called
			// at any time during execution...

			// First draw the background elements
			for (Element<Light> e : lightElements) {
				if (e.x.state().equals("RedNS_GreenEW")) {
					g.setColor(Color.GREEN);
				} else if (e.x.state().equals("RedNS_YellowEW")) {
					g.setColor(Color.YELLOW);
				} else {
					g.setColor(Color.RED);
				}
				XGraphics.fillOval(g, e.t, 0, 0, MP.carLength, VP.elementWidth);
			}
			g.setColor(Color.BLACK);
			for (Element<Road> e : roadElements) {
				XGraphics.fillRect(g, e.t, 0, 0, MP.roadLength, VP.elementWidth);
			}

			// Then draw the foreground elements
			for (Element<Road> e : roadElements) {
				// iterate through a copy because e.x.getCars() may change during iteration...
				for (Car d : e.x.getCars().toArray(new Car[0])) {
					g.setColor(d.getColor());
					XGraphics.fillOval(g, e.t, d.position(), 0, VP.elementWidth * (.1 * d.length()), VP.elementWidth);
				}
			}
		}
	}

	public void addSource(Source s, int i, int j, boolean eastToWest) {	}
	public void addIntersection(Intersection i, int j, int k) { }
	public void addSink(Sink z, int i, int j) {	}

	@Override
	public void addSource(Source s, int i, int j) {
		// TODO Auto-generated method stub
		
	}
}

