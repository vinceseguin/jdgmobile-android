package ca.qc.jeuxdegenie.jdgmobile.view;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.JsonDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.CompetitionTypesWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.Result;


public class ResultatFragment extends Fragment {
	
	ExpandableListView expListView;
	
	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.resultat_fragment, container, false);
		expListView = (ExpandableListView) view.findViewById(R.id.resultatExpList);
		
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new JsonDAO(new CompetitionTypesWorker(this)).execute();
	}
	
	public void updateContent(List<Result> competitions, List<List<Result>> competitionTypesChildren) {
		final ResultatFragmentAdapter rla = new ResultatFragmentAdapter(this, competitions, competitionTypesChildren);
		expListView.setAdapter(rla);
		
		//ajust group indicator arrow to the right side
		setGroupIndicatorToRight();
			
		expListView.setOnChildClickListener(new OnChildClickListener() {
			   @Override
			   public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				   //Create a new activity with the child's id
				   Result child = (Result) rla.getChild(groupPosition, childPosition);
				   
				   Intent intent = new Intent(getActivity(), ResultatDetailActivity.class);
				   intent.putExtra("leafId", child.getLeafId());
				   startActivity(intent);
				   
				   //Toast.makeText(getActivity(), child.toString() + " (leafId = " + child.getLeafId() +")", Toast.LENGTH_SHORT).show();
				   return false;
			   }
		});
	}
	
	// Convert pixel to dip
	private int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
		
	private void setGroupIndicatorToRight() {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		expListView.setIndicatorBounds(width - getDipsFromPixel(50), width - getDipsFromPixel(10));
	}

}
