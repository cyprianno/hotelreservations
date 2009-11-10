/**
 * 
 */
package pl.gapps.hotel.domain.dao.rp;

import pl.gapps.hotel.domain.dao.GenericDAO;
import pl.gapps.hotel.domain.model.rp.Floor;

/**
 * @author cyprian
 * DAO obsługujące tabelę Floor (pi�tra)
 */
public class FloorDAO extends GenericDAO<Floor>{

	@Override
	public Class<Floor> getEntityClass() {
		return Floor.class;
	}
}
