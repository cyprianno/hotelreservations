/**
 * 
 */
package pl.gapps.hotel.gxt.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.gapps.hotel.domain.model.rp.Hotel;
import pl.gapps.hotel.gxt.client.model.HotelModelData;

/**
 * @author cyprian
 *
 */

public class ClassPropertiesTest {
	private String testName = "testName";
	
	private Hotel createHotel(String name, String address, Long id) {
		Hotel h = new Hotel();
		h.setAddress(address);
		h.setName(name);
		h.setHotelId(id);
		return h;
	}

	@Test
	public void copyTest() {
		ClassProperties<Hotel, HotelModelData> cp = new ClassProperties<Hotel, HotelModelData>();
		Hotel hotel = createHotel(testName,"testAddress",1L);
		HotelModelData hotelModelData = new HotelModelData();
		cp.copy(hotel, hotelModelData);
		Assert.assertEquals(hotel.getAddress(), hotelModelData.getAddress());
		Assert.assertEquals(testName, hotelModelData.getName());
	}
	
	@Test
	public void copyListTest() {
		ClassProperties<Hotel, HotelModelData> cp = new ClassProperties<Hotel, HotelModelData>();
		List<Hotel> hotelList = new ArrayList<Hotel>();
		hotelList.add(createHotel(testName,"testAddress",1L));
		hotelList.add(createHotel(testName,"testAddress2",2L));
		List<HotelModelData> hotelModels = new ArrayList<HotelModelData>();
		cp.copyList(hotelList, hotelModels, Hotel.class, HotelModelData.class);
		Assert.assertEquals(2, hotelList.size());
		Assert.assertEquals(2, hotelModels.size());
	}
}
