package ca.qc.jeuxdegenie.jdgmobile.model.interfaces;

import org.json.JSONArray;

/**
 * 
 */
public interface IJsonBackgroundWorker {
	 
	public void doWork(JSONArray result);
	public String getUrl();
}
