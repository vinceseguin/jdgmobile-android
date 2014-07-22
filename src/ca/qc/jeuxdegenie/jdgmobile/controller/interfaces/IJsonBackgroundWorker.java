package ca.qc.jeuxdegenie.jdgmobile.controller.interfaces;

import org.json.JSONArray;

/**
 * 
 */
public interface IJsonBackgroundWorker {
	 
	public void doWork(JSONArray result);
	public String getUrl();
}
