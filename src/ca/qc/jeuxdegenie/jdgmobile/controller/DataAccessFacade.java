package ca.qc.jeuxdegenie.jdgmobile.controller;

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
	public void execute(IUpdatableContext context) {		
		SqLiteDAO sqLiteDAO = context.getSqLiteDAO();
		if (sqLiteDAO.isTableEmpty()) {
			context.getSqlDataUpdateJsonDAO().execute();
		}
		sqLiteDAO.execute();
	}
}
