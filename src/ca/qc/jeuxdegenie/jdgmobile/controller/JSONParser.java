package ca.qc.jeuxdegenie.jdgmobile.controller;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * http://www.learn2crack.com/2013/10/android-asynctask-json-parsing-example.html
 */
public class JSONParser {
	static InputStream is = null;
	private HttpHandler httpHandler = new HttpHandler();
	
	// constructor
	public JSONParser() {
		
	}
	
	public JSONObject getJSONObjectFromURL(String url) { 
		// try parse the string to a JSON object
		JSONObject jObj = null;
		try {
			jObj = new JSONObject(getJSONFromURL(url));
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		// return JSON String
		return jObj;
	}
	
	public JSONArray getJSONArrayFromURL(String url) { 
		// try parse the string to a JSON object
		JSONArray jArr = null;
		try {
			jArr = new JSONArray(getJSONFromURL(url));
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		// return JSON String
		return jArr;
	}
	
	private String getJSONFromURL(String url) {
		httpHandler = new HttpHandler();
		return httpHandler.getHTTPResponse(url);
	}
}
