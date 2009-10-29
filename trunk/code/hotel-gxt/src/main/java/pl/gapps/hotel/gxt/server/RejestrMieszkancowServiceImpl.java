/**
 * 
 */
package pl.gapps.hotel.gxt.server;

import java.util.ArrayList;
import java.util.List;

import pl.gapps.hotel.domain.model.rp.Hotel;
import pl.gapps.hotel.domain.service.rp.HotelService;
import pl.gapps.hotel.gxt.client.model.HotelModelData;
import pl.gapps.hotel.gxt.client.service.RejestrMieszkancowService;
import pl.gapps.hotel.gxt.utils.ClassProperties;

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
		HotelService hs = new HotelService();
		List<Hotel> hotels = hs.findAll();

		List<HotelModelData> returnList = new ArrayList<HotelModelData>();
		ClassProperties<Hotel, HotelModelData> cp = new ClassProperties<Hotel, HotelModelData>();
		cp.copyList(hotels, returnList, Hotel.class, HotelModelData.class);
		return returnList;
	}
	
	public PagingLoadResult<HotelModelData> getHotels(PagingLoadConfig loadConfig) {
		List<HotelModelData> hotels = getHotels();
		return new BasePagingLoadResult<HotelModelData>(hotels, loadConfig.getOffset(), hotels.size());
	}

	@Override
	public Boolean storeHotel(HotelModelData model) {
		HotelService hs = new HotelService();
		Hotel entity = new Hotel();
		entity.setAddress((String) model.get("address"));
		entity.setHotelId((Long) model.get("hotelId"));
		entity.setName((String) model.get("name"));
		return hs.store(entity);
	}

	@Override
	public Boolean storeHotels(List<HotelModelData> models) {
		// TODO save in one transaction
		for (HotelModelData hotelModelData : models) {
			storeHotel(hotelModelData);
		}
		return true;
	}
}
