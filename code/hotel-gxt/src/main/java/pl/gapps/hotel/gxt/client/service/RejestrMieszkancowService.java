/**
 * 
 */
package pl.gapps.hotel.gxt.client.service;

import java.util.List;

import pl.gapps.hotel.gxt.client.model.HotelModelData;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author cyprian
 *
 */
@RemoteServiceRelativePath("rmservice")
public interface RejestrMieszkancowService extends RemoteService {

	public List<HotelModelData> getHotels();
	public PagingLoadResult<HotelModelData> getHotels(PagingLoadConfig loadConfig);
	public Boolean storeHotel(HotelModelData model);
	public Boolean storeHotels(List<HotelModelData> models);
}
