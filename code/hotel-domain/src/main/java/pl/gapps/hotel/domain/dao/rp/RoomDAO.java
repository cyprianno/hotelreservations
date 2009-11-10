/**
 * 
 */
package pl.gapps.hotel.domain.dao.rp;

import pl.gapps.hotel.domain.dao.GenericDAO;
import pl.gapps.hotel.domain.model.rp.Room;

/**
 * @author cyprian
 * DAO obsługujące tabelę Room (pokoje)
 */
public class RoomDAO extends GenericDAO<Room>{

	@Override
	public Class<Room> getEntityClass() {
		return Room.class;
	}

}
