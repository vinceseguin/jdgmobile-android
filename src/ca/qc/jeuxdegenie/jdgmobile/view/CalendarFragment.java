package ca.qc.jeuxdegenie.jdgmobile.view;

import java.util.List;
import java.util.SortedMap;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.DataAccessFacade;
import ca.qc.jeuxdegenie.jdgmobile.controller.JsonDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.CalendarEventSqlDataUpdateJsonWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.CalendarEventsJsonWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.CalendarEventsSqLiteWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.Event;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.EventAdapter;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IUpdatableContext;

/**
 * http://fr.openclassrooms.com/informatique/cours/aller-plus-loin-dans-le-developpement-android/listfragment
 * http://www.ezzylearning.com/tutorial.aspx?tid=1763429
 * 
 * @author vgentilcore
 */
public class CalendarFragment extends ListFragment implements OnItemSelectedListener, IUpdatableContext {
	
	private SortedMap<CharSequence, List<Event>> data;
	private int selectedPosition = -1;
	
	private Spinner spinner;
	private ArrayAdapter<CharSequence> adapter;
	
	private JsonDAO jsonDAO;
	private JsonDAO sqlDataUpdatejsonDAO;
	private SqLiteDAO sqLiteDAO;
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.horaire_fragment, container, false);
		spinner = (Spinner) view.findViewById(R.id.spinner);
		
		return view;
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		DataAccessFacade.getInstance().execute(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		
		if (data != null) {
			updateContent(data);
		}
	}

	/**
	 * 
	 * @param items
	 */
	public void updateContent(final SortedMap<CharSequence, List<Event>> data) {
		
		this.data = data;
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		adapter = new ArrayAdapter<CharSequence>(getActivity(), 
								   android.R.layout.simple_spinner_item, 
								   data.keySet().toArray(new CharSequence[data.keySet().size()]));
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		// Select last selected date
		if (selectedPosition != -1) {
			spinner.setSelection(selectedPosition);
		}		
		
		// Register the item selected listener for the spinner...
		// When a date is selected, show only the events of that date
		spinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		// Keep track of selected date to use on resume...
		selectedPosition = spinner.getSelectedItemPosition();
		
		String selectedDate = (String) spinner.getSelectedItem();
		List<Event> selectedDates = data.get(selectedDate);
		
		EventAdapter ea = new EventAdapter(getActivity(), 
										   R.layout.calendar_item_row, 
										   selectedDates);
		setListAdapter(ea);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsonDAO getJsonDAO() {
		if (jsonDAO == null) {
			jsonDAO = new JsonDAO(new CalendarEventsJsonWorker(this));
		}
		return jsonDAO;
	}

	@Override
	public SqLiteDAO getSqLiteDAO() {
		if (sqLiteDAO == null) {
			sqLiteDAO = new SqLiteDAO(new CalendarEventsSqLiteWorker(this), getActivity());
		}
		return sqLiteDAO;
	}

	@Override
	public JsonDAO getSqlDataUpdateJsonDAO() {
		if (sqlDataUpdatejsonDAO == null) {
			sqlDataUpdatejsonDAO = new JsonDAO(new CalendarEventSqlDataUpdateJsonWorker(this));
		}
		return sqlDataUpdatejsonDAO;
	}
}
