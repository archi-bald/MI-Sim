package de.hawhamburg.mi.model.common.overlay;

import java.util.ArrayList;
import java.util.HashMap;
import de.hawhamburg.mi.model.common.Influences;
import de.hawhamburg.mi.model.common.Position;
import de.hawhamburg.mi.model.common.SimObjects;

/**
 * Interface das die Map Kapselt und Methoden die f�r die Verwendung von Agenten
 * und Wegfindung angewendet wird.
 * 
 * @author Markus
 * 
 */
public interface IMapUse {
	/**
	 * Gibt die SimObjekte, die sich um eine Position <b>pos</b> herum befinden
	 * zur�ck. Quasi die Nachbarfelder</br> Die Elemente liegen im Uhrzeigersinn
	 * in der ArrayList. Das erste Element ist das Element mit dem geringsten x
	 * und y Werten (Oben Links).
	 * 
	 * @param pos
	 * @return ArrayList<SimObjects>
	 */
	public ArrayList<SimObjects> getObjects360Grad(Position pos);

	/**
	 * Gibt eine HashMap mit den an Position <b>pos</b> befindlichen Einfl�ssen
	 * zur�ck.
	 * 
	 * @param pos
	 *            Position f�r die die Influences zur�ckgegeben werden sollen.
	 * @return HashMap<Influences, Intensity>
	 */
	public HashMap<Influences, Intensity> getInfluences(Position pos);

}
