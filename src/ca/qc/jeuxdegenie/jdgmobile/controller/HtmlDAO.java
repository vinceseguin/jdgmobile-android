package ca.qc.jeuxdegenie.jdgmobile.controller;

import ca.qc.jeuxdegenie.jdgmobile.controller.interfaces.IHtmlBackgroundWorker;
import android.os.AsyncTask;

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
