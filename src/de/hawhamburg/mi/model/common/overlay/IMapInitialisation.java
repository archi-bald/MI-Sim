package de.hawhamburg.mi.model.common.overlay;

import de.hawhamburg.mi.model.common.Position;
import de.hawhamburg.mi.model.common.SimObjects;

/**
 * Interface das die Overlay Map f�r die initialisierung implementiert.
 * 
 * @author Markus
 * 
 */
public interface IMapInitialisation {

	/**
	 * F�gt SimObjekt in die Overlay Map ein.
	 * 
	 * @param pos
	 *            Position an der simObj eingef�gt wird
	 * @param simObj
	 *            Objekt das an Position pos steht
	 * @return true wenn eingef�gt sonst false
	 */
	public boolean addSimObject(Position pos, SimObjects simObj);

}