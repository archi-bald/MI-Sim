package de.hawhamburg.mi.model.common.overlay;

import java.util.ArrayList;
import java.util.HashMap;

import de.hawhamburg.mi.model.common.Influences;
import de.hawhamburg.mi.model.common.Position;
import de.hawhamburg.mi.model.common.SimObjects;

/**
 * Klasse die das Overlay, die statischen Objekte der Simulation enthält.
 * 
 * @author Markus
 * 
 */
public class Map {
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
		for (int count2 = 0; count2 <= width; count2++) {
			ArrList1 = new ArrayList<HashMap<Influences, Intensity>>();
			for (int count1 = 0; count1 <= height; count1++) {
				ArrList1.add(new HashMap<Influences, Intensity>());
			}
			influenceSpace.add(ArrList1);
		}
		
		// ObjectSpace initialisieren
		ArrayList<SimObjects> ArrList2;
		for (int count2 = 0; count2 <= width; count2++) {
			ArrList2 = new ArrayList<SimObjects>();
			for (int count1 = 0; count1 <= height; count1++) {
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
		if (pos.getX() <= mapWidth && pos.getY() <= mapHeight) {
			return influenceSpace.get(pos.getY()).get(pos.getX());
		}
		throw new IllegalArgumentException("Position out of Map bounds.");
	}

	/**
	 * Gibt eine ArrayList von HashMaps zurück, die um einem Punkt <b>pos</b>
	 * auf einen dynamisches Objekt einwirken.</br> Die Elemente liegen im
	 * Uhrzeigersinn in der ArrayList. Das erste Element ist das Element mit dem
	 * geringsten x und y Werten (Oben Links).
	 * 
	 * @param pos
	 * @return Influence, Intensity - HashMap
	 */
	public ArrayList<SimObjects> getObjects360Grad(Position pos) {
		if (pos.getX() <= mapWidth && pos.getY() <= mapHeight) {
			ArrayList<SimObjects> ret = new ArrayList<>(8);

			ArrayList<SimObjects> row1 = objectSpace.get(pos.getY() - 1);
			ArrayList<SimObjects> row2 = objectSpace.get(pos.getY());
			ArrayList<SimObjects> row3 = objectSpace.get(pos.getY() + 1);

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
		throw new IllegalArgumentException("Position out of Map bounds.");
	}
}
