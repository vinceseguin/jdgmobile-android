package ca.qc.jeuxdegenie.jdgmobile.view;

import ca.qc.jeuxdegenie.jdgmobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class EventDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		
		Intent intent = getIntent();
		String description = intent.getStringExtra("description");
		WebView wv = (WebView) findViewById(R.id.decription);
		wv.loadData(description, "text/html; charset=UTF-8", null);
	}
}
