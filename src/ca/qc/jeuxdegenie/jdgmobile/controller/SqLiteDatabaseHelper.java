package ca.qc.jeuxdegenie.jdgmobile.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ca.qc.jeuxdegenie.jdgmobile.R;

/**
 * 
 * @author vgentilcore
 */
public class SqLiteDatabaseHelper extends SQLiteOpenHelper {
	
	private static final int DB_VERSION_NUMBER = 5;
	private String sqlCreateEventTable;
	private String sqlDropEventTable;
	private String sqlCreateResultTable;
	private String sqlDropResultTable;
	
	public SqLiteDatabaseHelper(Context context) {
		super(context, context.getResources().getText(R.string.databaseName).toString(), null, DB_VERSION_NUMBER);
		this.sqlCreateEventTable = context.getResources().getText(R.string.sqlCreateEventTable).toString();
		this.sqlDropEventTable = context.getResources().getText(R.string.sqlDropEventTable).toString();
		this.sqlCreateResultTable = context.getResources().getText(R.string.sqlCreateResultTable).toString();
		this.sqlDropResultTable = context.getResources().getText(R.string.sqlDropResultTable).toString();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreateEventTable);
		db.execSQL(sqlCreateResultTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(sqlDropEventTable);
		db.execSQL(sqlDropResultTable);
		onCreate(db);
	}
}
