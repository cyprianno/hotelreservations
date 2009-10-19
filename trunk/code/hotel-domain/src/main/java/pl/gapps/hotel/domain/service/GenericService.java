/**
 * 
 */
package pl.gapps.hotel.domain.service;

import java.io.Serializable;
import java.util.List;

import pl.gapps.hotel.domain.dao.GenericDAO;

/**
 * @author cyprian
 * Generyczny serwis zawieraj¹cy podstawowe metody obs³ugi tabel. 
 */
public abstract class GenericService<E extends Serializable, D extends GenericDAO<E>> {

	D dao;
	
	protected abstract D createDAO();
	
	protected D getDAO() {
		if (dao == null) {
			dao = createDAO();
		}
		return dao;
	}
	
	public List<E> findAll() {
		return dao.findAll();
	}
	
	public E findById(Serializable id) {
		return dao.findById(id);
	}
}
