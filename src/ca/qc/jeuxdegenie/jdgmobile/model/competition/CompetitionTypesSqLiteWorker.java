package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.ISqLiteBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultatFragment;

public class CompetitionTypesSqLiteWorker implements ISqLiteBackgroundWorker {

	private String tableName = "mobile_competition";
	
	private ResultatFragment context;
	
	public CompetitionTypesSqLiteWorker(ResultatFragment context) {
		this.context = context;
	}
	
	@Override
	public void doWork(Cursor cursor) {
		if (cursor.getCount() == 0) {
			return;
		}
		
		List<Result> competitionTypes = new ArrayList<Result>();
		
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Result competitionType = new Result(cursor);
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
	}

	@Override
	public String getTableName() {
		return tableName;
	}
}
