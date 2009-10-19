/**
 * 
 */
package pl.gapps.hotel.domain.dao;

import java.io.Serializable;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import pl.gapps.hotel.domain.dao.helper.PMF;

/**
 * @author cyprian
 * DAO generyczne po którym dziedzicz¹ wszystkie inne DAO; zawiera podstawowe operacje na bazie danych.
 */
public abstract class GenericDAO <E extends Serializable> {
	
	public abstract Class<E> getEntityClass();
	
	public PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}
	
	/**
	 * Zapisuje lub uaktualnia wpis.
	 * @param entity
	 * @return entity z zapisanym id (je¿eli nowe)
	 */
	public E store(E entity) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		E returnEntity = null;
		try {
			returnEntity = pm.makePersistent(entity);
		} finally {
			pm.close();
		}
		return returnEntity;
	}
	
	/**
	 * Pobiera listê wszystkich elementów z tabeli.
	 * @return Lista wpisów
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<E> returnData;
		Query query = pm.newQuery(getEntityClass());
		try {
			returnData = (List<E>) query.execute();
		} finally {
			pm.close();
		}
		return returnData;
		
	}
	
	/**
	 * Wyszukuje element wg podanego identyfikatora
	 * @param id
	 * @return znaleziony element
	 */
	public E findById(Serializable id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		E returnData;
		try {
			returnData = pm.getObjectById(getEntityClass(), id);
		} finally {
			pm.close();
		}
		return returnData;
	}
	
	/**
	 * Usuwa wpis z tabeli
	 * @param entity
	 */
	public void remove(E entity) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.deletePersistent(entity);
		} finally {
			pm.close();
		}
	}
}
