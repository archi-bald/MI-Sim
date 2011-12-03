package de.hawhamburg.mi.model.common.overlay;

/**
 * Gibt die Intensit�t einer Influence an. Hat einen Wertebereich von 0 bis 7.
 * 
 * @author Markus
 * 
 */
public class Intensity {
	private Integer value;

	/**
	 * Instantiiert eine neue Intensity instanz.
	 * 
	 * @param i
	 *            Wert der Intensit�t
	 * @return Intensity
	 */
	public static Intensity newIntensity(Integer i) {
		Intensity intens = new Intensity();
		intens.setValue(i);
		return intens;
	}

	/**
	 * Default Constructor private setzen um nur eingeschr�nkten Wertebereich
	 * zuzulassen.
	 * 
	 */
	private Intensity() {
	}

	/**
	 * Gibt den aktuellen Intensit�tswert zur�ck.
	 * 
	 * @return Intensit�tswert
	 */
	public Integer getValue() {
		return new Integer(value);
	}

	/**
	 * Setzen des Intensit�tswerts
	 * 
	 * @param i
	 */
	public void setValue(Integer i) {
		if (i < 9 && i >= 0) {
			value = new Integer(i);
		}
		throw new IllegalArgumentException(
				"Intensity needs to be a value between 0..7.");
	}
}
