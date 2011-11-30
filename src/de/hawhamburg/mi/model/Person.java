package de.hawhamburg.mi.model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;
import sim.util.MutableDouble2D;
import de.hawhamburg.mi.control.MiSimulation;
import de.hawhamburg.mi.model.common.DynamicEntity;

/**
 * The agent implementation
 * 
 * 
 *
 */
public class Person extends DynamicEntity implements Steppable{
	
	public Person(SimState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	public void step(SimState state) {		
		Continuous2D yard = miSimulation.world;
		Double2D me = miSimulation.world.getObjectLocation(this);
		MutableDouble2D sumForces = new MutableDouble2D();
		// add in a vector to the "teacher" -- the center of the yard, so we
		// don't go too far away
		sumForces.addIn(new Double2D((yard.width * 0.5 - me.x)
				* miSimulation.forceToSchoolMultiplier, (yard.height * 0.5 - me.y)
				* miSimulation.forceToSchoolMultiplier));
		// add a bit of randomness
		sumForces.addIn(new Double2D(miSimulation.randomMultiplier
				* (miSimulation.random.nextDouble() * 1.0 - 0.5),
				miSimulation.randomMultiplier
						* (miSimulation.random.nextDouble() * 1.0 - 0.5)));
		sumForces.addIn(me);
		miSimulation.world.setObjectLocation(this, new Double2D(sumForces));
	}
}
