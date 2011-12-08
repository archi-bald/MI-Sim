package de.hawhamburg.mi.model.common.overlay;

import java.util.ArrayList;
import java.util.HashMap;

import de.hawhamburg.mi.model.common.Influences;
import de.hawhamburg.mi.model.common.Position;
import de.hawhamburg.mi.model.common.SimObjects;

// TODO: Quellen - Ziele zurückgeben
// TODO: Eindimensionale Arraylist verwenden
/**
 * Klasse die das Overlay, die statischen Objekte der Simulation enthält.
 * 
 * @author Markus
 * 
 */
public class Map implements IMapInitialisation, IMapUse {
	/**
	 * 2D ArrayList der Influences in der Simulation
	 */
	private ArrayList<ArrayList<HashMap<Influences, Intensity>>> influenceSpace;
	/**
	 * 2D ArrayList der Objekte in der Simulation
	 */
	private ArrayList<ArrayList<SimObjects>> objectSpace;
	/**
	 * Höhe der Map
	 */
	private Integer mapHeight;
	/**
	 * Breite der Map
	 */
	private Integer mapWidth;

	/**
	 * Konstruktor für eine Map
	 * 
	 * @param height
	 *            Höhe der Map
	 * @param width
	 *            Breite der Map
	 */
	public Map(Integer height, Integer width) {
		// InfluenceSpace initialisieren
		ArrayList<HashMap<Influences, Intensity>> ArrList1;
		for (int count2 = 0; count2 <= height; count2++) {
			ArrList1 = new ArrayList<HashMap<Influences, Intensity>>();
			for (int count1 = 0; count1 <= width; count1++) {
				ArrList1.add(new HashMap<Influences, Intensity>());
			}
			influenceSpace.add(ArrList1);
		}

		// ObjectSpace initialisieren
		ArrayList<SimObjects> ArrList2;
		for (int count2 = 0; count2 <= height; count2++) {
			ArrList2 = new ArrayList<SimObjects>();
			for (int count1 = 0; count1 <= width; count1++) {
				ArrList2.add(SimObjects.NOTHING);
			}
			objectSpace.add(ArrList2);
		}

		// Objektvariablen
		mapHeight = height;
		mapWidth = width;
	}

	
	/**
	 * Gibt eine HashMap von Influences zurück, die an einem Punkt <b>pos</b>
	 * auf einen dynamisches Objekt einwirken.
	 * 
	 * @param pos
	 * @return Influence, Intensity - HashMap
	 */
	public HashMap<Influences, Intensity> getInfluences(Position pos) {
		notOutOfBoundsCheck(pos);
		return influenceSpace.get(pos.getY()).get(pos.getX());
	}

	@Override
	public ArrayList<SimObjects> getObjects360Grad(Position pos) {
		if (notOutOfBoundsCheck(pos)) {
			ArrayList<SimObjects> ret = new ArrayList<SimObjects>(8);

			ArrayList<SimObjects> row1 = objectSpace.get(pos.getY() - 1);
			ArrayList<SimObjects> row2 = objectSpace.get(pos.getY());
			ArrayList<SimObjects> row3 = objectSpace.get(pos.getY() + 1);
			
			// TODO: Randbereiche beachten und Nothing zurückgeben
			ret.add(row1.get(pos.getX() - 1));
			ret.add(row1.get(pos.getX()));
			ret.add(row1.get(pos.getX() + 1));
			ret.add(row2.get(pos.getX() - 1));
			ret.add(row2.get(pos.getX() + 1));
			ret.add(row3.get(pos.getX() - 1));
			ret.add(row3.get(pos.getX()));
			ret.add(row3.get(pos.getX() + 1));

			return ret;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see de.hawhamburg.mi.model.common.overlay.IMapInitialisation#addSimObject(de.hawhamburg.mi.model.common.Position, de.hawhamburg.mi.model.common.SimObjects)
	 */
	@Override
	public boolean addSimObject(Position pos, SimObjects simObj) {
		if (pos == null || simObj == null) {
			throw new IllegalArgumentException(
					"Position and SimObjects must not be null.");
		}
		notOutOfBoundsCheck(pos);
		objectSpace.get(pos.getY()).set(pos.getX(), simObj);

		// TODO: Influence Map pflegen

		return true;
	}

	/**
	 * Prüft das PositionsObjekt nicht ausserhalb der initialen Grenzen liegt.
	 * 
	 * @param pos
	 *            Position
	 * @throws IllegalArgumentException
	 *             Wenn out-of-Bounds
	 * @return true wenn alles Ok
	 */
	private boolean notOutOfBoundsCheck(Position pos) {
		if (!(pos.getX() <= mapWidth && pos.getY() <= mapHeight)) {
			throw new IndexOutOfBoundsException("Position out of Map bounds.");
		}
		return true;
	}


	@Override
	public HashMap<Influences, Intensity> getInfluence(Position pos) {
		// TODO: Influences an einer bestimmten Position zurückgeben.
		return null;
	}
}
