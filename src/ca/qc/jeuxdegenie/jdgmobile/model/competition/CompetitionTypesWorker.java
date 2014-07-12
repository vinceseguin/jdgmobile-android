package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IJsonBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultatFragment;

public class CompetitionTypesWorker implements IJsonBackgroundWorker {

	private static String url = "http://192.168.1.112/JDGMobile-Web/backend/WS/CompetitionWS.php?method=getCompetition";
	private ResultatFragment context;
	
	public CompetitionTypesWorker(ResultatFragment context) {
		this.context = context;
	}
	
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(JSONArray result) {
		try {
			List<Result> competitionTypes = new ArrayList<Result>();
			
			//Create objects from JSON
			for (int i=0; i<result.length(); i++) {
				JSONObject obj = result.getJSONObject(i);
				Result competitionType = new Result(obj);
				competitionTypes.add(competitionType);
			}
			
			//Get all the competitions (children) for each competition type
			List<List<Result>> competitionTypesChildren = new ArrayList<List<Result>>();
			for (Result competitionType : competitionTypes) {
				List<Result> competitions = new ArrayList<Result>();
				for(Result competition : competitionType.getItems()) {
					competitions.add(competition);
				}
				competitionTypesChildren.add(competitions);
			}
			
			context.updateContent(competitionTypes, competitionTypesChildren);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
