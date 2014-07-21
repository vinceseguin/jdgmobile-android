package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import android.content.ContentValues;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IHtmlBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultFragment;

public class CompetitionResultsSqlDataUpdateHtmlWorker implements IHtmlBackgroundWorker {
	
	private static final String TAG_COMPETITION_ID = "competition_id";
	private static final String TAG_COMPETITION_RESULT = "competition_result";
	
	private String tableName = "mobile_result";
	private String url = "backend/WS/CompetitionWS.php?method=getAllCompetitionResult";
	private ResultFragment context;
	
	public CompetitionResultsSqlDataUpdateHtmlWorker(ResultFragment context) {
		this.context = context;
		this.url = context.getText(R.string.backendLocation) + url;
	}
	
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(String result) {
		SqLiteDAO sqLiteDAO = new SqLiteDAO(context.getActivity());
		
		int competitionId = 1;
		for (String competitionResultHTML: result.split("COMPETITION")){
			
			//Insert the competition result in the database
			ContentValues values = new ContentValues();
			values.put(TAG_COMPETITION_RESULT, competitionResultHTML);
			String where = TAG_COMPETITION_ID + "=" + competitionId;
			
			sqLiteDAO.updateData(tableName, values, where, null);
			competitionId++;
		}    
	}
}
