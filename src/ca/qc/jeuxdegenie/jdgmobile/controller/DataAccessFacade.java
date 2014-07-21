package ca.qc.jeuxdegenie.jdgmobile.controller;

import android.content.Context;
import android.widget.Toast;
import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IUpdatableContext;

/**
 * 
 * @author vgentilcore
 */
public class DataAccessFacade {

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

	/**
	 * 
	 * @param context
	 */
	public void execute(IUpdatableContext iContext) {		
		SqLiteDAO sqLiteDAO = iContext.getSqLiteDAO();
		Context context = iContext.getContext();
		
		if (sqLiteDAO.isTableEmpty()) {
			//Test connectivity
			if(AppStatus.getInstance(context).isOnline()) {
				Toast.makeText(context, iContext.getUpdateMessage(), Toast.LENGTH_LONG).show();
				iContext.getSqlDataUpdateJsonDAO().execute();
			} else {
				Toast.makeText(context, context.getText(R.string.emptyDataOffline), Toast.LENGTH_LONG).show();
			}
		} else {
			//Test connectivity
			if(AppStatus.getInstance(context).isOnline()) {
				
				//TODO - Check DBVersion number and init DatabaseHelper with that version
				
				if (sqLiteDAO.isTableEmpty()) { //if data was obsolete
					iContext.getSqlDataUpdateJsonDAO().execute();
				}
			}
		}
		
		sqLiteDAO.execute();
	}
}
