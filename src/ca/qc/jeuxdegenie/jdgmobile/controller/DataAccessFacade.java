package ca.qc.jeuxdegenie.jdgmobile.controller;

import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.IUpdatableFragment;

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
	 * @param frag
	 */
	public void execute(IUpdatableFragment frag) {		
		SqLiteDAO sqLiteDAO = frag.getSqLiteDAO();
		if (sqLiteDAO.isTableEmpty()) {
			frag.getSqlDataUpdateJsonDAO().execute();
		}
		sqLiteDAO.execute();
	}
}
