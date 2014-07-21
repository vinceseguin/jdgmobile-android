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
import ca.qc.jeuxdegenie.jdgmobile.controller.DataAccessFacade;
import ca.qc.jeuxdegenie.jdgmobile.controller.JsonDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.CompetitionTypesJsonWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.CompetitionTypesSqLiteWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.CompetitionTypesSqlDataUpdateJsonWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.Result;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IUpdatableContext;

public class ResultFragment extends Fragment implements IUpdatableContext {
	
	private List<Result> competitionTypes;
	private List<List<Result>> competitionTypesChildren;
	
	private ExpandableListView expListView;
	private ResultFragmentAdapter rla;
	
	private JsonDAO jsonDAO;
	private JsonDAO sqlDataUpdatejsonDAO;
	private SqLiteDAO sqLiteDAO;
	
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
		//new use for online mode (updates competition list and their results subsequently)
		DataAccessFacade.getInstance().execute(this);
		
		//old use for online mode
		//new JsonDAO(new CompetitionTypesJsonWorker(this)).execute();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		if (competitionTypes != null && competitionTypesChildren != null) {
			updateContent(competitionTypes, competitionTypesChildren);
		}
	}

	public void updateContent(List<Result> competitionTypes, List<List<Result>> competitionTypesChildren) {
		
		this.competitionTypes = competitionTypes;
		this.competitionTypesChildren = competitionTypesChildren;
		
		rla = new ResultFragmentAdapter(this, competitionTypes, competitionTypesChildren);
		expListView.setAdapter(rla);
		
		//Adjust group indicator arrow to the right side
		setGroupIndicatorToRight();
			
		expListView.setOnChildClickListener(new OnChildClickListener() {
			   @Override
			   public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				   //Create a new activity with the child's id
				   Result child = (Result) rla.getChild(groupPosition, childPosition);
				   
				   Intent intent = new Intent(getActivity(), ResultDetailActivity.class);
				   intent.putExtra("leafId", child.getLeafId());
				   startActivity(intent);
				   
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

	@Override
	public JsonDAO getJsonDAO() {
		if (jsonDAO == null) {
			jsonDAO = new JsonDAO(new CompetitionTypesJsonWorker(this));
		}
		return jsonDAO;
	}

	@Override
	public SqLiteDAO getSqLiteDAO() {
		if (sqLiteDAO == null) {
			sqLiteDAO = new SqLiteDAO(new CompetitionTypesSqLiteWorker(this), getActivity());
		}
		return sqLiteDAO;
	}
	
	@Override
	public JsonDAO getSqlDataUpdateJsonDAO() {
		if (sqlDataUpdatejsonDAO == null) {
			sqlDataUpdatejsonDAO = new JsonDAO(new CompetitionTypesSqlDataUpdateJsonWorker(this));
		}
		return sqlDataUpdatejsonDAO;
	}
	
}
