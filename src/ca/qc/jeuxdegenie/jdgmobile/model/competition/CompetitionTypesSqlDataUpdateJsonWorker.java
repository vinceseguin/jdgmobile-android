package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import org.json.JSONArray;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IJsonBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultatFragment;

public class CompetitionTypesSqlDataUpdateJsonWorker implements IJsonBackgroundWorker {

	private String url = "backend/WS/CompetitionWS.php?method=getCompetition";
	private ResultatFragment context;
	
	public CompetitionTypesSqlDataUpdateJsonWorker(ResultatFragment context) {
		this.context = context;
		this.url = context.getText(R.string.backendLocation) + url;
	}
	
	@Override
	public void doWork(JSONArray result) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUrl() {
		return url;
	}
}
