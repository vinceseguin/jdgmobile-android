package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IHtmlBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultatDetailActivity;

public class CompetitionResultsWorker implements IHtmlBackgroundWorker {
	
	private String url = "backend/WS/CompetitionWS.php?method=getCompetitionResult&competitionId=";
	private ResultatDetailActivity context;
	
	public CompetitionResultsWorker(ResultatDetailActivity context) {
		this.context = context;
		this.url = context.getText(R.string.backendLocation) + url + context.getCompetitionId();
	}
	
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(String result) {
		context.updateContent(result);
	}

}
