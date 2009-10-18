/**
 * 
 */
package pl.gapps.hotel.gxt.client.view.rp;

import java.util.ArrayList;
import java.util.List;

import pl.gapps.hotel.gxt.client.model.HotelModelData;
import pl.gapps.hotel.gxt.client.service.RejestrMieszkancowService;
import pl.gapps.hotel.gxt.client.service.RejestrMieszkancowServiceAsync;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.DateWrapper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author cyprian
 * 
 */
public class HotelListContainer extends LayoutContainer {

	private final RejestrMieszkancowServiceAsync rmService = GWT
			.create(RejestrMieszkancowService.class);

	public HotelListContainer() {
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10));

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Name");
		column.setWidth(220);

		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId("address");
		column.setHeader("Address");
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setWidth(70);
		column.setEditor(new CellEditor(new TextField<String>()));

		configs.add(column);

		DateField dateField = new DateField();
		dateField.getPropertyEditor().setFormat(
				DateTimeFormat.getFormat("MM/dd/y"));

		column = new ColumnConfig();
		column.setId("available");
		column.setHeader("Available");
		column.setWidth(95);
		column.setEditor(new CellEditor(dateField));
		column.setDateTimeFormat(DateTimeFormat.getMediumDateFormat());
		configs.add(column);

		CheckColumnConfig checkColumn = new CheckColumnConfig("indoor",
				"Indoor?", 55);
		CellEditor checkBoxEditor = new CellEditor(new CheckBox());
		checkColumn.setEditor(checkBoxEditor);
		configs.add(checkColumn);

		RpcProxy<PagingLoadResult<HotelModelData>> proxy = new RpcProxy<PagingLoadResult<HotelModelData>>() {
			@Override
			public void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<HotelModelData>> callback) {
				rmService.getHotels((PagingLoadConfig) loadConfig, callback);
			}
		};

		// loader
		final BasePagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(
				proxy, new BeanModelReader());
		loader.setRemoteSort(true);
		loader.load();

		final ListStore<HotelModelData> store = new ListStore<HotelModelData>(loader);
		// TODO store.add(TestData.getPlants());

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		// cp.setIcon(Examples.ICONS.table());
		// TODO:
		cp.setHeading("Edit Hotel's buildings");
		cp.setFrame(true);
		cp.setSize(600, 300);
		cp.setLayout(new FitLayout());

		final RowEditor<HotelModelData> re = new RowEditor<HotelModelData>();
		final Grid<HotelModelData> grid = new Grid<HotelModelData>(store, cm);
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.addPlugin(checkColumn);
		grid.addPlugin(re);
		cp.add(grid);

		ToolBar toolBar = new ToolBar();
		Button add = new Button("Add Hotel");
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				HotelModelData hotel = new HotelModelData();
				hotel.set("name", "DS");
				// TODO:
				hotel.set("available", new DateWrapper().clearTime().asDate());

				re.stopEditing(false);
				store.insert(hotel, 0);
				re.startEditing(store.indexOf(hotel), true);

			}

		});
		toolBar.add(add);
		cp.setTopComponent(toolBar);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.addButton(new Button("Reset", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				store.rejectChanges();
			}
		}));

		cp.addButton(new Button("Save", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				store.commitChanges();
			}
		}));

		add(cp);
	}

	public void showPage() {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(this);
	}
}
