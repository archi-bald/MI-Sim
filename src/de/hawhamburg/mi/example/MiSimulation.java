package de.hawhamburg.mi.example;

import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class MiSimulation extends SimState {
	public Continuous2D yard = new Continuous2D(1.0, 100, 100);
	public int numStudents = 50;
	double forceToSchoolMultiplier = 0.01;
	double randomMultiplier = 0.1;
	
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
		yard.clear();
		// add some students to the yard
		for (int i = 0; i < numStudents; i++) {
			Person student = new Person();
			yard.setObjectLocation(student, new Double2D(yard.getWidth() * 0.5
					+ random.nextDouble() - 0.5, yard.getHeight() * 0.5
					+ random.nextDouble() - 0.5));
			schedule.scheduleRepeating(student);
		}
	}
}