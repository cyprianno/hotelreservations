/**
 * 
 */
package pl.gapps.hotel.domain.service.rp;

import pl.gapps.hotel.domain.dao.rp.FloorDAO;
import pl.gapps.hotel.domain.model.rp.Floor;
import pl.gapps.hotel.domain.service.GenericService;

/**
 * @author cyprian
 *
 */
public class FloorService extends GenericService<Floor, FloorDAO>{

	@Override
	protected FloorDAO createDAO() {
		return new FloorDAO();
	}

}
