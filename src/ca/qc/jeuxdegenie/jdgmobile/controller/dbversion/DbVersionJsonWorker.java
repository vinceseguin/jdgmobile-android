package ca.qc.jeuxdegenie.jdgmobile.controller.dbversion;

import android.app.Fragment;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.interfaces.IHtmlBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.interfaces.IUpdatableContext;

public class DbVersionJsonWorker implements IHtmlBackgroundWorker {

	private String url = "backend/WS/DbVersionWS.php?method=getDbVersionNumber";
	private IUpdatableContext context;
	
	public DbVersionJsonWorker(IUpdatableContext context) {
		this.context = context;
		this.url = ((Fragment)context).getText(R.string.backendLocation) + url;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(String result) {
		int db_version = Integer.parseInt(result);
		SqLiteDAO sqLiteDAO = context.getSqLiteDAO();
		sqLiteDAO.initSqLiteDatabaseHelper(db_version);
	}
}
