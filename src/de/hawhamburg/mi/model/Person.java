package de.hawhamburg.mi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.log4j.Logger;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Double2D;
import sim.util.MutableDouble2D;

import com.sun.tools.javac.util.Pair;

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
	 ArrayList<Position> path; 		// das aus der Wegfindung zurückgelieferte Path-Objekt
	 
	 
	/* 
	 * ALREADY DEFINED VARIABLES 
	 */
	private Position myPosition = null;	// current position in simulation world
	private Target myTarget = null; 		// the target the agent is heading to
	private Logger log = Logger.getRootLogger();
	private ArrayList<SenseObject> senseList = new ArrayList<SenseObject>();
	private SenseHistory senseHistory = new SenseHistory();
	
	public Person(SimState state) {
		super(state);
		myTarget = drawTarget();
		Double2D worldPos = miSimulation.world.getObjectLocation(this); 
		myPosition = Position.newPosition( (int) worldPos.x, (int) worldPos.y);
	}

	public void step(SimState state) {
		
		// check if agent has target
		if(myTarget == null){ // draw new target
			myTarget = drawTarget();
		} else if (myTarget.getPosition() == myPosition) { // check ob target erreicht ist
			// TODO: Was passiert, wenn Agent ein Target erreicht?
		} else {
			move();
		}
	}
	
	/**
	 * Requests all targets from SimStateObject and randomly selects a target 
	 */
	private Target drawTarget(){
		ArrayList<Target> targets = miSimulation.targetList;
		Random rand = new Random();
		float factor = rand.nextFloat();
		int pos = (int) (factor * targets.size());
		myTarget = targets.get(pos);
		return myTarget;
	}
	
	/**
	 * Move agent one step further along the path.
	 * 
	 * Removes the first element from the path as long as path is not empty
	 */
	private void move(){
		Double2D nextStepInPath = null;
		try{
			Position pos = path.remove(0);
			nextStepInPath = new Double2D(pos.getX(),pos.getY());
			miSimulation.world.setObjectLocation(this, nextStepInPath);
		} catch (IndexOutOfBoundsException e) {
			 log.debug("Path is empty in Person.move");
		}		
	}
	
	/**
	 * Sense the environment. Find out what influences the agent.
	 * 
	 * Gets the influences and their intensities at the current position and stores them
	 * for further analysis.
	 */
	private void sense(){
		HashMap<Influences, Intensity> influences = miSimulation.overlay.getInfluences(myPosition);
		SenseObject sensO = new SenseObject(myPosition, influences);
		senseHistory.addSensation(sensO);
	}
	
	/**
	 * 
	 * @author Pascal
	 *
	 * Currently a local class, we'll see where it gets us to.
	 * 
	 * 
	 */
	private class SenseObject{
		private Position pos;
		HashMap<Influences, Intensity> sensed;
		
		public SenseObject(Position pos, HashMap<Influences, Intensity> senses){
			this.pos = pos;
			sensed = senses;
		}
		
		
	}

	/**
	 * 
	 * SenseHistory keeps track of how the intensity changes along the routes an agents walks
	 * so we can perform analysis on this.
	 * 
	 * @author Pascal
	 *
	 */
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
