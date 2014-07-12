package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import ca.qc.jeuxdegenie.jdgmobile.view.ResultatDetailActivity;

public class CompetitionResultsWorker implements IHtmlBackgroundWorker {

	private String url = "http://192.168.1.112/JDGMobile-Web/backend/WS/CompetitionWS.php?method=getCompetitionResult&competitionId=";
	private ResultatDetailActivity context;
	
	public CompetitionResultsWorker(ResultatDetailActivity context) {
		this.context = context;
		this.url += context.getCompetitionId();
	}
	
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(String result) {
		context.updateContent(result);
	}

}
