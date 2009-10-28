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

/**
 * @author cyprian
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Hotel implements Serializable{
	
	private static final long serialVersionUID = 7768405242966642897L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long hotelId;
	
    @Persistent
	private String name;
	
    @Persistent
	private String address;
	
    @Persistent
	private Set<Room> rooms;
	
    @Persistent
	private Set<Floor> floors = new HashSet<Floor>();

    /* metody dostêpowe */
	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<Floor> getFloors() {
		return floors;
	}

	public void setFloors(Set<Floor> floors) {
		this.floors = floors;
	}

}
