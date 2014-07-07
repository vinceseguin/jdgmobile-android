package ca.qc.jeuxdegenie.jdgmobile.view;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class HoraireFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] list = new String[10];
		list[1] = "YEAH";
		
		int[] toViews = { 1 };
		
		//ListAdapter adapter = new ArrayAdapter<>(this.getActivity(), resource, objects)

		//this.setListAdapter(adapter);
	}
}
