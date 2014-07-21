package ca.qc.jeuxdegenie.jdgmobile.view;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import ca.qc.jeuxdegenie.jdgmobile.R;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		
		Tab tab = actionBar.newTab()
				.setText(R.string.horaire)
				.setTabListener(new TabListener<CalendarFragment>(
						this, "horaire", CalendarFragment.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab()
				.setText(R.string.resultats)
				.setTabListener(new TabListener<ResultFragment>(
						this, "resultats", ResultFragment.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab()
				.setText(R.string.partenaire)
				.setTabListener(new TabListener<PartnerFragment>(
						this, "partenaire", PartnerFragment.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab()
				.setText(R.string.apropos)
				.setTabListener(new TabListener<AboutFragment>(
						this, "apropos", AboutFragment.class));
		actionBar.addTab(tab);
	}
}
