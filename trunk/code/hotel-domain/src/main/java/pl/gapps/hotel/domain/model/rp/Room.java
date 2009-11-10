package pl.gapps.hotel.domain.model.rp;

import java.io.Serializable;

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
public class Room implements Serializable {

	private static final long serialVersionUID = -6819546681639818548L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key roomId;

	//TODO: Embeded room nr.
//    @Persistent
//	private RoomNumber number = new RoomNumber();
	
    @Persistent
	private Integer beds;
	
    @Persistent
	private Hotel hotel;
	
    @Persistent
	private Floor floor;

	/* metody dostępowe */
	public Key getRoomId() {
		return roomId;
	}

	public void setRoomId(Key roomId) {
		this.roomId = roomId;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

}
