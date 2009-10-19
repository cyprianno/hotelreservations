/**
 * 
 */
package pl.gapps.hotel.domain.service.rp;

import pl.gapps.hotel.domain.dao.rp.HotelDAO;
import pl.gapps.hotel.domain.model.rp.Hotel;
import pl.gapps.hotel.domain.service.GenericService;

/**
 * @author cyprian
 *
 */
public class HotelService extends GenericService<Hotel, HotelDAO>{

	@Override
	protected HotelDAO createDAO() {
		return new HotelDAO();
	}

}
