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
	
	private String sqlCreateEventTable;
	private String sqlDropEventTable;
	private String sqlCreateResultTable;
	private String sqlDropResultTable;
	
	public SqLiteDatabaseHelper(Context context, int db_version) {
		super(context, context.getResources().getText(R.string.databaseName).toString(), null, db_version);
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
		//user is simply offline now
		if(newVersion == DataAccessFacade.getOfflineVersionNumber()) { 
			//this doesn't seem to update the SCHEMA_VERSION (I guess that's only updated when we call the parent ctr : super(..))
			db.setVersion(oldVersion); // doesn't affect the fact that when the user reopens the app at a later time, it still thinks its at Integer.MAX_VALUE
		} else { 
			db.execSQL(sqlDropEventTable);
			db.execSQL(sqlDropResultTable);
			onCreate(db);
		}
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Do nothing...
		// Database will always be downgraded on creation because we pass 1 everytime.
		// We need to do this because we have to check in the database for the db_version value.
		// If database is outdated, tables will be recreated manually.
		
		
		//user was offline, don't drop everything.. but there's a risk he's missing updates
		if(oldVersion != DataAccessFacade.getOfflineVersionNumber()) { 
			db.execSQL(sqlDropEventTable);
			db.execSQL(sqlDropResultTable);
			onCreate(db);
		}
		//alternatively, if we always drop tables here, the user might be pulling the new data without any actual updates
		//if the db.setVersion(oldVersion) worked in the onUpgrade, onDowngrade wouldn't even be called after going back online.. unless there was a real new version..
		/*
		db.execSQL(sqlDropEventTable);
		db.execSQL(sqlDropResultTable);
		onCreate(db);
		*/
	}
}
