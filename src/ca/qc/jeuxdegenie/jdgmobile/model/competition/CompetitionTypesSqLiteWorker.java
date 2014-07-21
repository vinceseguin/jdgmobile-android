package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.ISqLiteBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.ResultFragment;

public class CompetitionTypesSqLiteWorker implements ISqLiteBackgroundWorker {

	private String tableName = "mobile_result";
	
	private ResultFragment context;
	
	public CompetitionTypesSqLiteWorker(ResultFragment context) {
		this.context = context;
	}
	
	@Override
	public void doWork(Cursor cursor) {
		if (cursor.getCount() == 0) {
			return;
		}
		
		List<Result> competitionTypes = new ArrayList<Result>();
		List<List<Result>> competitionTypesChildren = new ArrayList<List<Result>>();
		List<Integer> insertedCompetitionTypesId = new ArrayList<Integer>();
		
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Result competition = new Result(cursor);
			if(!competition.isLeaf()) {
				competitionTypes.add(competition);
			}else {
				//make sure the competition type (parent) exists before adding it
				if(!insertedCompetitionTypesId.contains(competition.getCompetitionType())) {
					insertedCompetitionTypesId.add(competition.getCompetitionType());
					//create the children list for that parent
					competitionTypesChildren.add(competition.getCompetitionType(), new ArrayList<Result>());
				}
				competitionTypesChildren.get(competition.getCompetitionType()).add(competition);
			}
		}
		
		context.updateContent(competitionTypes, competitionTypesChildren);
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
