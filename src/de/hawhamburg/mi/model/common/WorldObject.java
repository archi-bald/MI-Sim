package de.hawhamburg.mi.model.common;

import de.hawhamburg.mi.control.MiSimulation;
import sim.engine.SimState;
import sim.util.Double2D;

/**
 * A WorldObject is basically everything with a spacial representation in the simulation.
 * Therefore it has a position.
 * 
 * @author pascal
 *
 */
public class WorldObject {
	protected MiSimulation miSimulation;
	
	public WorldObject(SimState state){
		miSimulation = (MiSimulation) state;
	}
	
	public Double2D getPosition(){
		return miSimulation.world.getObjectLocation(this);
	}

}
