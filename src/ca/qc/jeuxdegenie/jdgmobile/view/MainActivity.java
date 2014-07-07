package ca.qc.jeuxdegenie.jdgmobile.view;

import ca.qc.jeuxdegenie.jdgmobile.R;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);

		Tab tab = actionBar.newTab()
				.setText(R.string.horaire)
				.setTabListener(new TabListener<HoraireFragment>(
						this, "horaire", HoraireFragment.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab()
				.setText(R.string.resultats)
				.setTabListener(new TabListener<ResultatFragment>(
						this, "resultats", ResultatFragment.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab()
				.setText(R.string.partenaire)
				.setTabListener(new TabListener<PartenaireFragment>(
						this, "partenaire", PartenaireFragment.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab()
				.setText(R.string.apropos)
				.setTabListener(new TabListener<AProposFragment>(
						this, "apropos", AProposFragment.class));
		actionBar.addTab(tab);
	}
}
