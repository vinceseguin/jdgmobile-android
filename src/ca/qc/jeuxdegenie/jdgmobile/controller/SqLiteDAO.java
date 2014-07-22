package ca.qc.jeuxdegenie.jdgmobile.controller;

import ca.qc.jeuxdegenie.jdgmobile.R;
import ca.qc.jeuxdegenie.jdgmobile.controller.interfaces.ISqLiteBackgroundWorker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

/**
 * 
 * @author vgentilcore
 */
public class SqLiteDAO extends AsyncTask<String, String, Cursor> {

	private static final int DB_VERSION = 6;
	
	private ISqLiteBackgroundWorker backgroundWorker;	
	private SqLiteDatabaseHelper databaseHelper;
	private Context context;

	public SqLiteDAO(ISqLiteBackgroundWorker backgroundWorker, Context context) {
		this.context = context;
		this.backgroundWorker = backgroundWorker;
		initSqLiteDatabaseHelper(DB_VERSION);
	}
	
	public void initSqLiteDatabaseHelper(int db_version) {
		this.databaseHelper = new SqLiteDatabaseHelper(context, db_version);
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
	public long insertData(ContentValues values) {
		SQLiteDatabase bdd = databaseHelper.getWritableDatabase();
		return bdd.insert(backgroundWorker.getTableName(), null, values);
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

	/**
	 * 
	 * @return
	 */
	public int getDbVersion() {
		SQLiteDatabase bdd = databaseHelper.getWritableDatabase();
		
		// Check if db_version table exists...
		Cursor c = bdd.rawQuery(context.getText(R.string.sqlDbVersionTableExists).toString(), 
								new String[] {context.getText(R.string.sqlDbVersionTableName).toString()});
		c.moveToFirst();
		boolean tableExists = c.getInt(0) > 0;
		
		// If db_version table exists, return actual value...
		if (tableExists) {
			c = bdd.query(context.getText(R.string.sqlDbVersionTableName).toString(), null, null, null, null, null, null);
			c.moveToFirst();
			return c.getInt(0);
		}
		
		// Else, create table and init. value to 1
		else {
			bdd.execSQL(context.getText(R.string.sqlCreateDbVersionTable).toString());
			ContentValues values = new ContentValues();
			values.put(context.getText(R.string.sqlDbVersionColumnName).toString(), 1);
			bdd.insert(context.getText(R.string.sqlDbVersionTableName).toString(), null, values);
		}
		
		return 1;
	}
	
	/**
	 * 
	 */
	public int incrementDbVersion() {
		SQLiteDatabase bdd = databaseHelper.getWritableDatabase();
		
		// Get current db_version value...		
		Cursor c = bdd.query(context.getText(R.string.sqlDbVersionTableName).toString(), null, null, null, null, null, null);
		c.moveToFirst();
		int db_version = c.getInt(0);
		
		// Increment db_version value in database...
		ContentValues values = new ContentValues();
		values.put(context.getText(R.string.sqlDbVersionColumnName).toString(), db_version + 1);
		return bdd.update(context.getText(R.string.sqlDbVersionTableName).toString(), values, null, null);
	}
}