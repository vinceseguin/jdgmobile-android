package ca.qc.jeuxdegenie.jdgmobile.controller.interfaces;

import android.database.Cursor;

public interface ISqLiteBackgroundWorker {

	public void doWork(Cursor cursor);
	public String getTableName();
}
