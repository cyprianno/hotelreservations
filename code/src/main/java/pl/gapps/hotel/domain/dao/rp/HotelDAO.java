/**
 * 
 */
package pl.gapps.hotel.domain.dao.rp;

import pl.gapps.hotel.domain.dao.GenericDAO;
import pl.gapps.hotel.domain.model.rp.Hotel;

/**
 * @author cyprian
 * DAO obs³uguj¹ce tabelê Hotel (budynki)
 */
public class HotelDAO extends GenericDAO<Hotel>{

	@Override
	public Class<Hotel> getEntityClass() {
		return Hotel.class;
	}

}
