/**
 * 
 */
package pl.gapps.hotel.gxt.client.view.rp;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author cyprian
 *
 */
public class HotelListContainer extends LayoutContainer {
	public HotelListContainer() {
		Text label = new Text("Test label");
		add(label);
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
	}
	
	public void showPage() {
        RootPanel.get("content").clear();
        RootPanel.get("content").add(this);
	}
}
