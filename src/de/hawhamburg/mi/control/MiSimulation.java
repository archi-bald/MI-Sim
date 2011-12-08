package de.hawhamburg.mi.control;

import java.util.ArrayList;

import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;
import de.hawhamburg.mi.model.Person;
import de.hawhamburg.mi.model.common.Target;
import de.hawhamburg.mi.model.common.overlay.Map;

public class MiSimulation extends SimState {
	
	/*
	 * @author Pascal
	 * Hier hängt dann der Map-Layer von Markus drin.
	 * Targets werde in einer separaten Liste auch hier festgehalten?!
	 */
	public static Map overlay = new Map(100, 100);
	public static Pathfinder pathfinder = new Pathfinder();
	public static ArrayList<Target> targetList;
	
	public Continuous2D world = new Continuous2D(1.0, 100, 100);
	public int numPeople = 50;
	public double forceToSchoolMultiplier = 0.01;
	public double randomMultiplier = 0.1;
	
	public MiSimulation(long seed) {
		super(seed);
	}

	public static void main(String[] args) {
		doLoop(MiSimulation.class, args);
		System.exit(0);
	}

	public void start() {
		super.start();
		// clear the yard
		world.clear();
		// add some students to the yard
		for (int i = 0; i < numPeople; i++) {
			Person person = new Person(this);
			world.setObjectLocation(person, new Double2D(world.getWidth() * 0.5
					+ random.nextDouble() - 0.5, world.getHeight() * 0.5
					+ random.nextDouble() - 0.5));
			schedule.scheduleRepeating(person);
		}
	}
}