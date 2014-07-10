package ca.qc.jeuxdegenie.jdgmobile.view;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.JSONCalendarDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.Event;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.EventAdapter;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * http://fr.openclassrooms.com/informatique/cours/aller-plus-loin-dans-le-developpement-android/listfragment
 * 
 * @author vgentilcore
 */
public class HoraireFragment extends ListFragment {

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
	
	/**
	 * 
	 * @param items
	 */
	public void updateContent(Event[] items) {
		EventAdapter ea = new EventAdapter(getActivity(), 
										   R.layout.calendar_item_row, 
										   items);
		setListAdapter(ea);
	}
}
