package ca.qc.jeuxdegenie.jdgmobile.controller.competition;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.interfaces.IHtmlBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultDetailActivity;

public class CompetitionResultsHtmlWorker implements IHtmlBackgroundWorker {
	
	private String url = "backend/WS/CompetitionWS.php?method=getCompetitionResult&competitionId=";
	private ResultDetailActivity context;
	
	public CompetitionResultsHtmlWorker(ResultDetailActivity context) {
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
