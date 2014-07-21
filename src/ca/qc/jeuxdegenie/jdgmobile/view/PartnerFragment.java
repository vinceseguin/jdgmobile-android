package ca.qc.jeuxdegenie.jdgmobile.view;

import ca.qc.jeuxdegenie.jdgmobile.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PartnerFragment extends Fragment {

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.partenaire_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		WebView wv = (WebView) getActivity().findViewById(R.id.partenaires);
		WebSettings settings = wv.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		wv.loadUrl("file:///android_asset/www/partners/partners.html");
	}
}
