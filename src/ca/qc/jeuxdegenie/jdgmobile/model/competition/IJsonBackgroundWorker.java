package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import org.json.JSONArray;

/**
 * 
 */
public interface IJsonBackgroundWorker {
	 
	public void doWork(JSONArray result);
	public String getUrl();
}
