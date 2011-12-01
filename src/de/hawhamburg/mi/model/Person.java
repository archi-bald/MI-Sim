package de.hawhamburg.mi.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;
import sim.util.MutableDouble2D;
import de.hawhamburg.mi.control.MiSimulation;
import de.hawhamburg.mi.model.common.DynamicEntity;
import de.hawhamburg.mi.model.common.Target;

/**
 * The agent implementation
 * 
 * 
 *
 */
public class Person extends DynamicEntity implements Steppable{
	
	/* 
	 * UPCOMING VARIABLES
	 */ 
	 ArrayList<Object> path; 		// das aus der Wegfindung zurückgelieferte Path-Objekt
	 
	 
	/* 
	 * ALREADY DEFINED VARIABLES 
	 */
	private Double2D myPosition;	// current position in simulation world
	private Logger log = Logger.getRootLogger();
	
	public Person(SimState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	public void step(SimState state) {		
		
		myPosition = miSimulation.world.getObjectLocation(this);
		
	}
	
	/**
	 * Requests all targets from SimStateObject and randomly selects a target 
	 */
	private Target drawTarget(){
		return null;
	}
	
	/**
	 * Move agent one step further along the path.
	 * 
	 * Removes the first element from the path as long as path is not empty
	 */
	private void move(){
		MutableDouble2D nextWorldPosition = new MutableDouble2D();
		try{
			Object nextStepInPath = path.remove(0);
		} catch (IndexOutOfBoundsException e) {
			 log.debug("Path is empty in Person.move");
		}
		// ggf. umrechnung von PathIndices in WorldKoordinaten
		miSimulation.world.setObjectLocation(this, new Double2D(nextWorldPosition));
	}
	
	
}
