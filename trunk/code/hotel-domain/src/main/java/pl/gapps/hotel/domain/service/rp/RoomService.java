/**
 * 
 */
package pl.gapps.hotel.domain.service.rp;

import pl.gapps.hotel.domain.dao.rp.RoomDAO;
import pl.gapps.hotel.domain.model.rp.Room;
import pl.gapps.hotel.domain.service.GenericService;

/**
 * @author cyprian
 *
 */
public class RoomService extends GenericService<Room, RoomDAO>{

	@Override
	protected RoomDAO createDAO() {
		return new RoomDAO();
	}

}
