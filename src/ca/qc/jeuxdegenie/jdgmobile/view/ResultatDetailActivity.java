package ca.qc.jeuxdegenie.jdgmobile.view;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.HtmlDAO;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.CompetitionResultsWorker;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ResultatDetailActivity extends Activity {

	private int competitionId;
	
	public int getCompetitionId() {
		return competitionId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		
		Intent intent = getIntent();
		competitionId = intent.getIntExtra("leafId", 0);
		new HtmlDAO(new CompetitionResultsWorker(this)).execute();
	}
	
	public void updateContent(String result) {
		WebView wv = (WebView) findViewById(R.id.decription);
		wv.loadData(result, "text/html; charset=UTF-8", null);
	}
}
