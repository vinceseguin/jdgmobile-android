package ca.qc.jeuxdegenie.jdgmobile.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.DataAccessFacade;
import ca.qc.jeuxdegenie.jdgmobile.controller.HtmlDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.JsonDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.competition.CompetitionResultsSqLiteWorker;
import ca.qc.jeuxdegenie.jdgmobile.controller.dbversion.DbVersionJsonWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.interfaces.IUpdatableContext;

public class ResultDetailActivity extends Activity implements IUpdatableContext {

	private int competitionId;
	private SqLiteDAO sqLiteDAO;
	private HtmlDAO htmlDAO;
	
	public int getCompetitionId() {
		return competitionId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		
		Intent intent = getIntent();
		competitionId = intent.getIntExtra("leafId", 0);
		DataAccessFacade.getInstance().execute(this);
		
		//no need to call this WS anymore, all data is local
		//mobile_result table should already have the results in it after the result tab was opened
		//new HtmlDAO(new CompetitionResultsHtmlWorker(this)).execute();
	}
	
	public void updateContent(String result) {
		WebView wv = (WebView) findViewById(R.id.decription);
		wv.loadData(result, "text/html; charset=UTF-8", null);
	}

	@Override
	public JsonDAO getJsonDAO() {
		// TODO Auto-generated method stub
		
		//should never be called
		return null;
	}

	@Override
	public SqLiteDAO getSqLiteDAO() {
		if (sqLiteDAO == null) {
			sqLiteDAO = new SqLiteDAO(new CompetitionResultsSqLiteWorker(this), this);
		}
		return sqLiteDAO;
	}

	@Override
	public JsonDAO getSqlDataUpdateJsonDAO() {
		// TODO Auto-generated method stub
		
		//should never be called (table will be populated already)
		return null;
	}
	
	@Override
	public HtmlDAO getDbVersionHtmlDAO() {
		if (htmlDAO == null) {
			htmlDAO = new HtmlDAO(new DbVersionJsonWorker(this));
		}
		return htmlDAO; 
	}
	
	@Override
	public Context getContext() {
		return this;
	}

	@Override
	public String getUpdateMessage() {
		return this.getText(R.string.resultUpdate).toString();
	}
}
