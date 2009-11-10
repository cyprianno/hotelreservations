/**
 * 
 */
package pl.gapps.hotel.domain.dao.rm;

import pl.gapps.hotel.domain.dao.GenericDAO;
import pl.gapps.hotel.domain.model.rm.Client;

/**
 * @author cyprian
 * DAO obsługujące tabelę klientów
 */
public class ClientDAO extends GenericDAO<Client>{

	@Override
	public Class<Client> getEntityClass() {
		return Client.class;
	}

}
