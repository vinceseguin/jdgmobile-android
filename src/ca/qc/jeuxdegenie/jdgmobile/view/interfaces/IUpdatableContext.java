package ca.qc.jeuxdegenie.jdgmobile.view.interfaces;

import ca.qc.jeuxdegenie.jdgmobile.controller.HtmlDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.JsonDAO;
import ca.qc.jeuxdegenie.jdgmobile.controller.SqLiteDAO;
import android.content.Context;

public interface IUpdatableContext {

	public JsonDAO getJsonDAO();
	public SqLiteDAO getSqLiteDAO();
	public JsonDAO getSqlDataUpdateJsonDAO();
	public HtmlDAO getDbVersionHtmlDAO();
	public Context getContext();
	public String getUpdateMessage();
}