package ca.qc.jeuxdegenie.jdgmobile.controller;

import ca.qc.jeuxdegenie.jdgmobile.R;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author vgentilcore
 */
public class SqLiteDatabaseHelper extends SQLiteOpenHelper {
	
	String sqlCreateTable;
	String sqlDropTable;
	
	public SqLiteDatabaseHelper(Context context) {
		super(context, context.getResources().getText(R.string.databaseName).toString(), null, 1);
		sqlCreateTable = context.getResources().getText(R.string.sqlCreateEventTable).toString();
		sqlDropTable = context.getResources().getText(R.string.sqlDropEventTable).toString();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreateTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(sqlDropTable);
		onCreate(db);
	}
}
