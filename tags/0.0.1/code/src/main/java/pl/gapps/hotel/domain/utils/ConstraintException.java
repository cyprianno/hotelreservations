/**
 * 
 */
package pl.gapps.hotel.domain.utils;

/**
 * @author cyprian
 * Wyj¹tek rzucany, gdy nie s¹ spe³nione warunki wykonania metody.
 */
public class ConstraintException extends Exception {

	private static final long serialVersionUID = 1203030729111562356L;
	
	public ConstraintException(String msg) {
		super(msg);
	}

}
