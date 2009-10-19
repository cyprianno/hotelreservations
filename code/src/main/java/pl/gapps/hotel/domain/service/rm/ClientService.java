/**
 * 
 */
package pl.gapps.hotel.domain.service.rm;

import pl.gapps.hotel.domain.dao.rm.ClientDAO;
import pl.gapps.hotel.domain.model.rm.Client;
import pl.gapps.hotel.domain.service.GenericService;

/**
 * @author cyprian
 *
 */
public class ClientService extends GenericService<Client, ClientDAO>{

	@Override
	protected ClientDAO createDAO() {
		return new ClientDAO();
	}

}
