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
	private IUpdatableContext iContext;
	
	private static final int OFFLINE_VERSION_NUMBER = Integer.MAX_VALUE;

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
	
	public static int getOfflineVersionNumber() {
		return OFFLINE_VERSION_NUMBER;
	}	
	
	/**
	 * 
	 * @param context
	 */
	public void execute(IUpdatableContext iContext) {
		this.iContext = iContext;
		
		// Check for db_version... Must be called before anything else (if there's internet)!
		dbVersion = OFFLINE_VERSION_NUMBER;
		//SQLiteDatabase db = 
		//dbVersion = db.getVersion();
		
		//Test connectivity
		if(AppStatus.getInstance(iContext.getContext()).isOnline()) {
			HtmlDAO htmlDAO = iContext.getDbVersionHtmlDAO();
			htmlDAO.execute();
		} else { 
			onPostExecute(dbVersion);
		}
	}
	
	public void onPostExecute(int db_version){
		this.dbVersion = db_version;
		
		Context context = iContext.getContext();
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
