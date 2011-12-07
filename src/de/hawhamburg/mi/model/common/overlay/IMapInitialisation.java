package de.hawhamburg.mi.model.common.overlay;

import de.hawhamburg.mi.model.common.Position;
import de.hawhamburg.mi.model.common.SimObjects;

/**
 * Interface das die Overlay Map für die initialisierung implementiert.
 * 
 * @author Markus
 * 
 */
public interface IMapInitialisation {

	/**
	 * Fügt SimObjekt in die Overlay Map ein.
	 * 
	 * @param pos
	 *            Position an der simObj eingefügt wird
	 * @param simObj
	 *            Objekt das an Position pos steht
	 * @return true wenn eingefügt sonst false
	 */
	public boolean addSimObject(Position pos, SimObjects simObj);

}