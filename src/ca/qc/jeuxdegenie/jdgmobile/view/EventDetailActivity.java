package ca.qc.jeuxdegenie.jdgmobile.view;

import ca.qc.jeuxdegenie.jdgmobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class EventDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		
		Intent intent = getIntent();
		String description = intent.getStringExtra("description");
		TextView tv = (TextView) findViewById(R.id.decription);
		tv.setText(Html.fromHtml(description));
		tv.setMovementMethod(new ScrollingMovementMethod());
	}
}
