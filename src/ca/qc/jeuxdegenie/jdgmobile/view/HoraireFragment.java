package ca.qc.jeuxdegenie.jdgmobile.view;

import java.util.List;
import java.util.SortedMap;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.JSONCalendarDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.Event;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.EventAdapter;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * http://fr.openclassrooms.com/informatique/cours/aller-plus-loin-dans-le-developpement-android/listfragment
 * http://www.ezzylearning.com/tutorial.aspx?tid=1763429
 * 
 * @author vgentilcore
 */
public class HoraireFragment extends ListFragment {
	
	SortedMap<CharSequence, List<Event>> data;
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.horaire_fragment, container, false);
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
		new JSONCalendarDAO(this).execute();
	}
	
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		
		if (data != null) {			
			this.updateContent(data);
		}
	}

	/**
	 * 
	 * @param items
	 */
	public void updateContent(final SortedMap<CharSequence, List<Event>> data) {
		
		this.data = data;
		
		final Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), 
											   android.R.layout.simple_spinner_item, 
											   data.keySet().toArray(new CharSequence[data.keySet().size()]));
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		// Register the item selected listener for the spinner...
		// When a date is selected, show only the events of that date
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
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
		});
	}
}
