package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IJsonBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IUpdatableFragment;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultatFragment;

public class CompetitionTypesSqlDataUpdateJsonWorker implements IJsonBackgroundWorker {

	private static final String TAG_COMPETITION_TYPE_ID = "competition_type_id";
	private static final String TAG_COMPETITION_TYPE_NAME = "competition_type_name";
	private static final String TAG_COMPETITION_ID = "competition_id";
	private static final String TAG_COMPETITION_NAME = "competition_name";
	private static final String TAG_COMPETITION_TYPE = "competition_type";
	private static final String TAG_COMPETITION_RESULT = "competition_result";
	
	private String url = "backend/WS/CompetitionWS.php?method=getCompetition";
	private ResultatFragment context;
	
	public CompetitionTypesSqlDataUpdateJsonWorker(ResultatFragment context) {
		this.context = context;
		this.url = context.getText(R.string.backendLocation) + url;
	}
	
	@Override
	public void doWork(JSONArray result) {
		IUpdatableFragment frag = (IUpdatableFragment) context;		
		SqLiteDAO sqLiteDAO = frag.getSqLiteDAO();
		
		try {
			List<Result> competitionTypes = new ArrayList<Result>();
			
			//Create objects from JSON
			for (int i=0; i<result.length(); i++) {
				JSONObject obj = result.getJSONObject(i);
				Result competitionType = new Result(obj);
				competitionTypes.add(competitionType);
				
				//Insert the competition types in the database
				ContentValues values = new ContentValues();
				values.put(TAG_COMPETITION_TYPE_ID, i);
				values.put(TAG_COMPETITION_TYPE_NAME, competitionType.getName());
				sqLiteDAO.insertData(values);
			}
			
			//Get all the competitions (children) for each competition type
			for (int i=0; i<competitionTypes.size(); i++) {
				Result competitionType = competitionTypes.get(i);
				
				for(Result competition : competitionType.getItems()) {
					//Insert the competitions in the database
					ContentValues values = new ContentValues();
					values.put(TAG_COMPETITION_ID, competition.getLeafId());
					values.put(TAG_COMPETITION_NAME, competition.getName());
					values.put(TAG_COMPETITION_TYPE, i);
					sqLiteDAO.insertData(values);
				}	
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUrl() {
		return url;
	}
}
