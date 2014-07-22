package ca.qc.jeuxdegenie.jdgmobile.controller;

import android.content.Context;
import android.widget.Toast;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.view.interfaces.IUpdatableContext;

/**
 * 
 * @author vgentilcore
 */
public class DataAccessFacade {

	private int dbVersion = -1;

	private static volatile DataAccessFacade instance = null;

	private DataAccessFacade() {

	}

	public final static DataAccessFacade getInstance() {
		if (instance == null) {
			synchronized (DataAccessFacade.class) {
				if (instance == null) {
					instance = new DataAccessFacade();
				}
			}
		}
		return instance;
	}

	public int getDbVersion() {
		return dbVersion;
	}
	
	public void setDbVersion(int dbVersion) {
		this.dbVersion = dbVersion;
	}
	
	/**
	 * 
	 * @param context
	 */
	public void execute(IUpdatableContext iContext) {
		Context context = iContext.getContext();
		
		// Check for db_version... Must be called before anything else !
		dbVersion = -1;
		HtmlDAO htmlDAO = iContext.getDbVersionHtmlDAO();
		htmlDAO.execute();
		
		// Attente active...
		try {
			while (dbVersion == -1) {
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqLiteDAO sqLiteDAO = iContext.getSqLiteDAO();
		if (sqLiteDAO.isTableEmpty()) {
			//Test connectivity
			if(AppStatus.getInstance(context).isOnline()) {
				Toast.makeText(context, iContext.getUpdateMessage(), Toast.LENGTH_LONG).show();
				iContext.getSqlDataUpdateJsonDAO().execute();
			} else {
				Toast.makeText(context, context.getText(R.string.emptyDataOffline), Toast.LENGTH_LONG).show();
			}
		}
		
		sqLiteDAO.execute();
	}
}
