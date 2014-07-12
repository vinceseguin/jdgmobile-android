package ca.qc.jeuxdegenie.jdgmobile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * http://www.learn2crack.com/2013/10/android-asynctask-json-parsing-example.html
 */
public class JSONParser {
	static InputStream is = null;
	
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
		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			// HttpPost httpPost = new HttpPost(url);
			// HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String json = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		return json;
	}
}
