package ca.qc.jeuxdegenie.jdgmobile.view;

import ca.qc.jeuxdegenie.jdgmobile.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AProposFragment extends Fragment {

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.apropos_fragment, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		WebView wv = (WebView) getActivity().findViewById(R.id.apropos);
		WebSettings settings = wv.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		wv.loadUrl("file:///android_asset/www/about/about.html");
	}
}
