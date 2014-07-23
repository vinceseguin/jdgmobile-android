package ca.qc.jeuxdegenie.jdgmobile.controller.dbversion;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.DataAccessFacade;
import ca.qc.jeuxdegenie.jdgmobile.controller.interfaces.IHtmlBackgroundWorker;
import ca.qc.jeuxdegenie.jdgmobile.view.interfaces.IUpdatableContext;

public class DbVersionJsonWorker implements IHtmlBackgroundWorker {

	private String url = "backend/WS/DbVersionWS.php?method=getDbVersionNumber";
	
	public DbVersionJsonWorker(IUpdatableContext iContext) {
		this.url = iContext.getContext().getText(R.string.backendLocation) + url;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void doWork(String result) {
		int db_version = Integer.parseInt(result.trim());
		DataAccessFacade.getInstance().onPostExecute(db_version);
	}
}
