package ca.qc.jeuxdegenie.jdgmobile.controller;

import android.os.AsyncTask;
import ca.qc.jeuxdegenie.jdgmobile.model.competition.IHtmlBackgroundWorker;

public class HtmlDAO extends AsyncTask<String, String, String>{

	private IHtmlBackgroundWorker backgroundWorker;
	
	public HtmlDAO(IHtmlBackgroundWorker backgroundWorker) {
		this.backgroundWorker = backgroundWorker;
	}

	@Override
	protected String doInBackground(String... params) {
		HttpHandler httpHandler = new HttpHandler();
		String response = httpHandler.getHTTPResponse(backgroundWorker.getUrl()); 
		return response;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		backgroundWorker.doWork(result);	
	}

}
