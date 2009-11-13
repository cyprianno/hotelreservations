/**
 * 
 */
package pl.gapps.hotel.gxt.client.view.rp;

import java.util.ArrayList;
import java.util.List;

import pl.gapps.hotel.gxt.client.enums.RowStatusEnum;
import pl.gapps.hotel.gxt.client.icons.ResourceManager;
import pl.gapps.hotel.gxt.client.model.HotelModelData;
import pl.gapps.hotel.gxt.client.service.RejestrMieszkancowServiceAsync;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.util.DateWrapper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author cyprian
 * 
 */
public class HotelListContainer extends LayoutContainer {

	Text deleteCountText = new Text("Do usunięcia: ");
	Text deleteCount = new Text("0");
	String statusColumnName = "statusColumn";
	Button removeHotel;

	public HotelListContainer() {
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout());

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

		GridCellRenderer<ModelData> statusRenderer = new GridCellRenderer<ModelData>() {
			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				Text output = new Text();
				if (model.get(property) == null) {
					model.set(property, RowStatusEnum.OK);
				}
				RowStatusEnum value = model.get(property);
				output.setText(value.getLabel());
				output.setStyleAttribute("color", value.getColor());
				return output;
			}
		};
		column = new ColumnConfig();
		column.setId(statusColumnName);
		column.setWidth(95);
		column.setHeader("status");
		column.setRenderer(statusRenderer);
		configs.add(column);

		RpcProxy<PagingLoadResult<HotelModelData>> proxy = new RpcProxy<PagingLoadResult<HotelModelData>>() {
			@Override
			public void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<HotelModelData>> callback) {
				RejestrMieszkancowServiceAsync.Util.getInstance().getHotels(
						(PagingLoadConfig) loadConfig, callback);
			}
		};

		// loader
		final BasePagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(
				proxy, new BeanModelReader());
		loader.setRemoteSort(true);
		loader.load();

		final ListStore<ModelData> store = new ListStore<ModelData>(loader);

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setHeading("Edit Hotel's buildings");
		cp.setFrame(true);
		cp.setHeight(300);
		cp.setLayout(new FitLayout());

		final RowEditor<HotelModelData> re = new RowEditor<HotelModelData>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		final Grid<ModelData> grid = new Grid<ModelData>(store, cm);
		grid.setLoadMask(true);
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<ModelData> se) {
				if (se.getSelectedItem() == null) {
					removeHotel.setEnabled(false);
				} else {
					removeHotel.setEnabled(true);
				}
				
			}
		});
		cp.add(grid);

		ToolBar toolBar = new ToolBar();
		Button add = new Button("Add Hotel");
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				HotelModelData hotel = new HotelModelData();
				BeanModelFactory factory = BeanModelLookup.get().getFactory(
						hotel.getClass());
				if (factory == null) {
					throw new RuntimeException("No BeanModelFactory found for "
							+ hotel.getClass());
				}
				hotel.set("name", "DS");
				hotel.set("available", new DateWrapper().clearTime().asDate());
				hotel.set(statusColumnName,RowStatusEnum.NEW);

				re.stopEditing(false);
				BeanModel model = factory.createModel(hotel);
				store.insert(model, 0);
				re.startEditing(store.indexOf(model), true);

			}

		});
		add.setIcon(ResourceManager.ICONS.add16());
		toolBar.add(add);
		removeHotel = new Button("RemoveHotel",new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItem() != null) {
					store.getRecord(grid.getSelectionModel().getSelectedItem()).set(statusColumnName, RowStatusEnum.REMOVED);
				}
			}
		});
		removeHotel.setEnabled(false);
		toolBar.add(removeHotel);

		deleteCount.setStyleAttribute("color", "black");
		toolBar.add(deleteCountText);
		toolBar.add(deleteCount);
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
				List<HotelModelData> datas = new ArrayList<HotelModelData>();
				List<HotelModelData> removeRows = new ArrayList<HotelModelData>();
				for (Record record : store.getModifiedRecords()) {
					HotelModelData modelData =(HotelModelData) ((BeanModel) record.getModel())
					.getBean(); 
					if (record.get(statusColumnName).equals(RowStatusEnum.REMOVED)) {
						removeRows.add(modelData);
					} else {
						datas.add(modelData);
					}
				}
				RejestrMieszkancowServiceAsync.Util.getInstance().removeHotels(removeRows, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						MessageBox mb = new MessageBox();
						mb.setMessage("Error \n "
								+ caught.getLocalizedMessage());
						mb.show();
						throw new RuntimeException(caught);
					}

					@Override
					public void onSuccess(Boolean result) {
						MessageBox mb = new MessageBox();
						mb.setMessage("Deleted");
						mb.show();
						store.commitChanges();
//						loader.load();
					}
				});
				RejestrMieszkancowServiceAsync.Util.getInstance().storeHotels(
						datas, new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {
								MessageBox mb = new MessageBox();
								mb.setMessage("Error \n "
										+ caught.getLocalizedMessage());
								mb.show();
								throw new RuntimeException(caught);
							}

							@Override
							public void onSuccess(Boolean result) {
								MessageBox mb = new MessageBox();
								mb.setMessage("Saved");
								mb.show();
								store.commitChanges();
								loader.load();
							}
						});
			}
		}));

		add(cp);
	}

	public void showPage() {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(this);
	}
}
