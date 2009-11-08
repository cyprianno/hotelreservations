/**
 * 
 */
package pl.gapps.hotel.gxt.client.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BeanModelTag;

/**
 * @author cyprian
 *
 */
public class HotelModelData extends BaseModelData implements Serializable, BeanModelTag {

	private static final long serialVersionUID = 4015228790784747291L;
	
    /* metody dostepowe */
	public Long getHotelId() {
		return get("hotelId");
	}

	public void setHotelId(Long hotelId) {
		set("hotelId",hotelId);
	}

	public String getName() {
		return get("name");
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getAddress() {
		return get("address");
	}

	public void setAddress(String address) {
		set("address", address);
	}
}
