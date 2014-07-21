package ca.qc.jeuxdegenie.jdgmobile.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import ca.qc.jeuxdegenie.jdgmobile.model.interfaces.ISqLiteBackgroundWorker;

/**
 * 
 * @author vgentilcore
 */
public class SqLiteDAO extends AsyncTask<String, String, Cursor> {

	private ISqLiteBackgroundWorker backgroundWorker;	
	private SqLiteDatabaseHelper databaseHelper;

	public SqLiteDAO(ISqLiteBackgroundWorker backgroundWorker, Context context) {
		this.backgroundWorker = backgroundWorker;
		this.databaseHelper = new SqLiteDatabaseHelper(context);
	}

	@Override
	protected Cursor doInBackground(String... params) {
		SQLiteDatabase bdd = databaseHelper.getWritableDatabase();
		Cursor cursor = bdd.query(backgroundWorker.getTableName(), null, null, null, null, null, null);
		return cursor;
	}

	@Override
	protected void onPostExecute(Cursor cursor) {
		super.onPostExecute(cursor);		
		backgroundWorker.doWork(cursor);
		databaseHelper.close();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTableEmpty() {
		SQLiteDatabase bdd = databaseHelper.getReadableDatabase();
		Cursor cursor = bdd.query(backgroundWorker.getTableName(), null, null, null, null, null, null);
		boolean result = (!cursor.moveToFirst());
		return result;
	}

	/**
	 * 
	 * @param values
	 */
	public void insertData(ContentValues values) {
		SQLiteDatabase bdd = databaseHelper.getWritableDatabase();
		bdd.insert(backgroundWorker.getTableName(), null, values);
	}

	/**
	 * 
	 * @param values
	 * @param where
	 * @param whereValues
	 */
	public void updateData(ContentValues values, String where, String[] whereValues) {
		SQLiteDatabase bdd = databaseHelper.getWritableDatabase();
		bdd.update(backgroundWorker.getTableName(), values, where, whereValues);
	}
}