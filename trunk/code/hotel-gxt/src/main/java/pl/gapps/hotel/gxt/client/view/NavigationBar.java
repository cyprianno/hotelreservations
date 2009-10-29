/**
 * 
 */
package pl.gapps.hotel.gxt.client.view;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author cyprian
 * Pasek nawigacyjny na górze strony
 */
public class NavigationBar extends LayoutContainer {
	public NavigationBar() {
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout());
		ContentPanel cp = new ContentPanel();
		cp.setFrame(true);
		cp.setHeaderVisible(false);
		cp.setLayout(new HBoxLayout());
		Button rpModuleButton = new Button("Rejestr Pomieszczeń");
		cp.add(rpModuleButton);
		Button rmModuleButton = new Button("Rejestr Mieszkańców");
		cp.add(rmModuleButton);
		this.add(cp);
	}
	
	public void show() {
		RootPanel.get("navigationBar").clear();
		RootPanel.get("navigationBar").add(this);
	}

}
