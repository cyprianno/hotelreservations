/**
 * 
 */
package pl.gapps.hotel.domain.model.rp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author cyprian
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Floor implements Serializable {

	private static final long serialVersionUID = 3622294280184295856L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key floorId;
	
    @Persistent
	private String floorNr;
	
    @Persistent
	private Set<Room> rooms = new HashSet<Room>();
	
    @Persistent
	private Hotel hotel;

    /* metody dostêpowe */
	public Key getFloorId() {
		return floorId;
	}

	public void setFloorId(Key floorId) {
		this.floorId = floorId;
	}

	public String getFloorNr() {
		return floorNr;
	}

	public void setFloorNr(String floorNr) {
		this.floorNr = floorNr;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
