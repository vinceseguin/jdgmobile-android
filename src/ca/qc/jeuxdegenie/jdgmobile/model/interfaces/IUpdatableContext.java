package ca.qc.jeuxdegenie.jdgmobile.model.interfaces;

import android.content.Context;
import ca.qc.jeuxdegenie.jdgmobile.controller.JsonDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;

public interface IUpdatableContext {

	public JsonDAO getJsonDAO();
	public SqLiteDAO getSqLiteDAO();
	public JsonDAO getSqlDataUpdateJsonDAO();
	public Context getContext();
	public String getUpdateMessage();
}