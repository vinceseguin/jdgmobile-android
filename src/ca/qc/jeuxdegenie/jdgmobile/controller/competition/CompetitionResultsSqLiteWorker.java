package ca.qc.jeuxdegenie.jdgmobile.controller.competition;

import android.database.Cursor;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.interfaces.ISqLiteBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.Result;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultDetailActivity;

public class CompetitionResultsSqLiteWorker implements ISqLiteBackgroundWorker {

	private String tableName = "mobile_result";
	
	private ResultDetailActivity context;
	
	public CompetitionResultsSqLiteWorker(ResultDetailActivity context) {
		this.context = context;
	}
	
	@Override
	public void doWork(Cursor cursor) {
		if (cursor.getCount() == 0) {
			return;
		}
		
		String competitionResults = ""; 
		//find the right competition in the query result.. since it selects the whole table
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			//if its a competition
			if(!cursor.isNull(Result.NUM_COL_COMPETITION_ID)) { 
				//if its the right competition
				if(cursor.getInt(Result.NUM_COL_COMPETITION_ID) == context.getCompetitionId()) {
					competitionResults = cursor.getString(Result.NUM_COL_COMPETITION_RESULT);
				}
			}
			
		}
		
		context.updateContent(competitionResults);
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	public String getCreateTableQuery() {
		return context.getResources().getText(R.string.sqlCreateResultTable).toString();
	}

	public String getDropTableQuery() {
		return context.getResources().getText(R.string.sqlDropResultTable).toString();
	}
}
