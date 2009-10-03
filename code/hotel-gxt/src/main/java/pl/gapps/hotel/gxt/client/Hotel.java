/**
 * 
 */
package pl.gapps.hotel.gxt.client;

import pl.gapps.hotel.gxt.client.view.rp.HotelListContainer;

import com.google.gwt.core.client.EntryPoint;

/**
 * @author cyprian
 *
 */
public class Hotel implements EntryPoint{

	public void onModuleLoad() {
		HotelListContainer hlc = new HotelListContainer();
		hlc.showPage();
		
	}

}
