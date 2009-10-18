/**
 * 
 */
package pl.gapps.hotel.gxt.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import pl.gapps.hotel.domain.model.rp.Floor;
import pl.gapps.hotel.domain.model.rp.Hotel;
import pl.gapps.hotel.domain.model.rp.Room;
import pl.gapps.hotel.gxt.client.model.HotelModelData;
import pl.gapps.hotel.gxt.client.service.RejestrMieszkancowService;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author cyprian
 *
 */
public class RejestrMieszkancowServiceImpl extends RemoteServiceServlet implements RejestrMieszkancowService {

	private static final long serialVersionUID = -971841361499859672L;

	@Override
	public List<HotelModelData> getHotels() {
//		HotelService hs = new HotelService();
//		List<Hotel> hotels = hs.findAll();
		List<Hotel> hotels = new ArrayList<Hotel>();
		Hotel h = new Hotel();
		h.setAddress("adres");
		h.setFloors(new HashSet<Floor>());
		h.setRooms(new HashSet<Room>());
		h.setHotelId(1);
		h.setName("name");
		hotels.add(h);
		//
		List<HotelModelData> returnList = new ArrayList<HotelModelData>();
		for (Hotel hotel : hotels) {
			HotelModelData hmd = new HotelModelData();
			hmd.set("address", hotel.getAddress());
			hmd.set("floorCount", hotel.getFloors().size());
			hmd.set("hotelId", hotel.getHotelId());
			hmd.set("name", hotel.getName());
			hmd.set("roomCount", hotel.getRooms().size());
			returnList.add(hmd);
		}
		return returnList;
	}
	
	public PagingLoadResult<HotelModelData> getHotels(PagingLoadConfig loadConfig) {
		List<HotelModelData> hotels = getHotels();
		System.out.println(hotels.size());
		return new BasePagingLoadResult<HotelModelData>(hotels, loadConfig.getOffset(), hotels.size());
	}

}
