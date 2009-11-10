/**
 * 
 */
package pl.gapps.hotel.domain.dao.rp;

import pl.gapps.hotel.domain.dao.GenericDAO;
import pl.gapps.hotel.domain.model.rp.Hotel;

/**
 * @author cyprian
 * DAO obsługujące tabelę Hotel (budynki)
 */
public class HotelDAO extends GenericDAO<Hotel>{

	@Override
	public Class<Hotel> getEntityClass() {
		return Hotel.class;
	}

}
