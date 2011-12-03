package de.hawhamburg.mi.model.common.overlay;

/**
 * Gibt die Intensität einer Influence an. Hat einen Wertebereich von 0 bis 7.
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
	 *            Wert der Intensität
	 * @return Intensity
	 */
	public static Intensity newIntensity(Integer i) {
		Intensity intens = new Intensity();
		intens.setValue(i);
		return intens;
	}

	/**
	 * Default Constructor private setzen um nur eingeschränkten Wertebereich
	 * zuzulassen.
	 * 
	 */
	private Intensity() {
	}

	/**
	 * Gibt den aktuellen Intensitätswert zurück.
	 * 
	 * @return Intensitätswert
	 */
	public Integer getValue() {
		return new Integer(value);
	}

	/**
	 * Setzen des Intensitätswerts
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
