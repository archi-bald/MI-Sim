package de.hawhamburg.mi.view;

import java.awt.Color;

import javax.swing.JFrame;

import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;
import de.hawhamburg.mi.control.MiSimulation;

public class MiSimulationWithUI extends GUIState {

	public Display2D display;
	public JFrame displayFrame;
	ContinuousPortrayal2D yardPortrayal = new ContinuousPortrayal2D();

	public static void main(String[] args) {
		MiSimulationWithUI vid = new MiSimulationWithUI();
		Console c = new Console(vid);
		c.setVisible(true);
	}

	public MiSimulationWithUI() {
		super(new MiSimulation(System.currentTimeMillis()));
	}

	public MiSimulationWithUI(SimState state) {
		super(state);
	}

	public static String getName() {
		return "MI Wohlfühl und Gefahrensimulation";
	}

	public void start() {
		super.start();
		setupPortrayals();
	}

	public void load(SimState state) {
		super.load(state);
		setupPortrayals();
	}

	public void setupPortrayals() {
		MiSimulation students = (MiSimulation) state;
		// tell the portrayals what to portray and how to portray them
		yardPortrayal.setField(students.yard);
		yardPortrayal.setPortrayalForAll(new OvalPortrayal2D());
		// reschedule the displayer
		display.reset();
		display.setBackdrop(Color.white);
		// redraw the display
		display.repaint();
	}

	public void init(Controller c) {
		super.init(c);
		display = new Display2D(600, 600, this);
		display.setClipping(false);
		displayFrame = display.createFrame();
		displayFrame.setTitle("MI Simulation");
		c.registerFrame(displayFrame); // so the frame appears in the "Display"
										// list
		displayFrame.setVisible(true);
		display.attach(yardPortrayal, "World");
	}

	public void quit() {
		super.quit();
		if (displayFrame != null)
			displayFrame.dispose();
		displayFrame = null;
		display = null;
	}
}