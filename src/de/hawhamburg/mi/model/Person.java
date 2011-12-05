package de.hawhamburg.mi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.sun.tools.javac.util.Pair;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;
import sim.util.MutableDouble2D;
import de.hawhamburg.mi.control.MiSimulation;
import de.hawhamburg.mi.model.common.DynamicEntity;
import de.hawhamburg.mi.model.common.Influences;
import de.hawhamburg.mi.model.common.Position;
import de.hawhamburg.mi.model.common.Target;
import de.hawhamburg.mi.model.common.overlay.Intensity;

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
	private Position myPosition;	// current position in simulation world
	private Target myTarget; 		// the target the agent is heading to
	private Logger log = Logger.getRootLogger();
	private ArrayList<SenseObject> senseList = new ArrayList<SenseObject>();
	private SenseHistory senseHistory = new SenseHistory();
	
	public Person(SimState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	public void step(SimState state) {	
		
		// get current position from SimulationWorld
		Double2D worldPos = miSimulation.world.getObjectLocation(this); 
		myPosition = Position.newPosition( (int) worldPos.x, (int) worldPos.y);
		// TODO: calculate internal state and further actions
	}
	
	/**
	 * Requests all targets from SimStateObject and randomly selects a target 
	 */
	private Target drawTarget(){
		// TODO: draw target
		return myTarget;
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
	
	/**
	 * Sense the environment. Find out what influences the agent.
	 * 
	 * Gets the influences and their intensities at the current position and stores them
	 * for further analysis.
	 */
	private void sense(){
		HashMap<Influences, Intensity> influences = miSimulation.overlay.getInfluences(myPosition);
		
	}
	
	/**
	 * 
	 * @author Pascal
	 *
	 * Currently local, we'll see where it gets us to.
	 */
	private class SenseObject{
		private Position pos;
		
		
	}

	private class SenseHistory{
		int moves; // the number of moves the agent has made
		HashMap<Influences, Integer> sensedInfluences = 
										new HashMap<Influences, Integer>();
		
		public void addSensation(SenseObject sensobj){
			// zerlege das senseobject und adde seine Teile in die sensedInfluences
			addInfluence(null);
		}
		
		private void addInfluence(Pair<Influences, Integer> influence){
			int intensity = sensedInfluences.get(influence.fst);
			sensedInfluences.put(influence.fst, intensity+influence.snd);
		}
		
		public HashMap<Influences, Integer> calculateHistory(){
			// deep copying the hashmap
			HashMap<Influences, Integer>  copy = new HashMap<Influences, Integer>();
			Iterator<Entry<Influences, Integer>> it = sensedInfluences.entrySet().iterator();
			while(it.hasNext()){
				copy.put(it.next().getKey(), it.next().getValue());
			}
			
			// level values down with number of moves made
			Iterator<Entry<Influences, Integer>> it2 = copy.entrySet().iterator();
			while(it2.hasNext()){
				Entry<Influences, Integer> influ = it2.next();
				copy.put(influ.getKey(), influ.getValue()/moves);
			}
			
			return copy;
		}
		
	}
}
