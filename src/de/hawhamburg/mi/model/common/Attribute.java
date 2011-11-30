package model.common;

/**
 * An Attribute something by which an object A (with this Attribute) can influence the state 
 * of another object B, if B is sensible for this type of Attribute. 
 * 
 * @author pascal
 *
 */
public class Attribute {
	
	private int intensity;

	public Attribute(int intensity) {
		this.intensity = intensity;
	}

	public int getIntensity() {
		return intensity;
	}

}
