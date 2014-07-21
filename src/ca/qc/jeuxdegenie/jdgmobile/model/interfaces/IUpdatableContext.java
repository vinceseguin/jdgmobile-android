package ca.qc.jeuxdegenie.jdgmobile.model.interfaces;

import ca.qc.jeuxdegenie.jdgmobile.controller.JsonDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;

public interface IUpdatableContext {

	public JsonDAO getJsonDAO();
	public SqLiteDAO getSqLiteDAO();
	public JsonDAO getSqlDataUpdateJsonDAO();
}