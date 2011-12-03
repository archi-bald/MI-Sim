package de.hawhamburg.mi.model.common;

/**
 * Representiert einen Punkt auf der Map.
 * @author Markus
 */
public class Position {
	/**
	 * Position in x-Richtung
	 */
	private int x;
	/**
	 * Position in y-Richtung
	 */
	private int y;

	/**
	 * Konstruktor für einen Punkt.
	 * 
	 * @param x
	 *            Position in x-Richtung
	 * @param y
	 *            Position in y-Richtung
	 */
	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Konstruieren neuer Positionsobjekt mithilfe dieser Methode.
	 * 
	 * @param x
	 *            Position in x-Richtung
	 * @param y
	 *            Position in y-Richtung
	 * @return Positions Objekt
	 */
	public static Position newPosition(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Position must be greater 0.");
		}
		return new Position(x, y);
	}

	/**
	 * Position in x-Richtung
	 * 
	 * @return x-Position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Position in y-Richtung
	 * 
	 * @return y-Position
	 */
	public int getY() {
		return y;
	}

}
