/**
 * 
 */
package pl.gapps.hotel.domain.utils;

/**
 * @author cyprian
 * Wyjątek rzucany, gdy nie są spełnione warunki wykonania metody.
 */
public class ConstraintException extends Exception {

	private static final long serialVersionUID = 1203030729111562356L;
	
	public ConstraintException(String msg) {
		super(msg);
	}

}
