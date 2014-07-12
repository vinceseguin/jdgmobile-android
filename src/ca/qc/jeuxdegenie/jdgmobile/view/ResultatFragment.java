package ca.qc.jeuxdegenie.jdgmobile.view;

import java.util.List;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.JSONResultatDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.Event;
import ca.qc.jeuxdegenie.jdgmobile.model.calendar.EventAdapter;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.Result;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.ResultAdapter;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ResultatFragment extends ListFragment {

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.resultat_fragment, container, false);
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
		new JSONResultatDAO(this).execute();
	}
	
	/**
	 * 
	 * @param items
	 */
	public void updateContent(final List<Result> data) {
		
		ArrayAdapter<Result> aa = new ResultAdapter(getActivity(), 
													android.R.layout.simple_list_item_1,
													data);
		setListAdapter(aa);
	}
}
