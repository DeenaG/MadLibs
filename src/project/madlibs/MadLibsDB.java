package project.madlibs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MadLibsDB {
	//database constants
	public static final String DB_NAME = "madlib.db";
	public static final int DB_VERSION = 1;
	
	//table constants
	public static final String MADLIB_TABLE = "MadLib";
	public static final String MADLIB_ID = "_id";
	public static final int MADLIB_ID_COL = 0;
	public static final String MADLIB_STORY = "story";
	public static final int MADLIB_STORY_COL = 1;
	
	public static final String CREATE_MADLIB_TABLE = "CREATE TABLE IF NOT EXISTS " + MADLIB_TABLE + " (" + MADLIB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ MADLIB_STORY + " TEXT NOT NULL);";
	
	public static final String DROP_LIST_TABLE = "DROP TABLE IF EXISTS " + MADLIB_TABLE;
	private static class DBHelper extends SQLiteOpenHelper{
		public DBHelper(Context context, String name, CursorFactory factory, int version){
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_MADLIB_TABLE);
			db.execSQL(CREATE_MADLIB_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(MadLibsDB.DROP_LIST_TABLE);
		}
	}
	
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	public MadLibsDB(Context context){
		dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
	}
	
	//private methods to help read and write to the database
	
	//opens read-only connection to database
	private void openReadableDB(){
		db = dbHelper.getReadableDatabase();
	}
	
	//opens read-write connection to database
	private void openWritableDB(){
		db = dbHelper.getWritableDatabase();
	}
	
	private void closeDB(){
		if (db != null)
			db.close();
	}
	
	
	
	//get a random for from the table return as a MadLibs object
	public MadLibs getRandomMadLib(){
		String query = MADLIB_TABLE + " ORDER BY RANDOM() LIMIT 1 ";
		this.openReadableDB();
		Cursor cursor = this.db.query(query, new String[] { "*" }, null, null, null, null, null);
		cursor.moveToFirst();
		MadLibs madLibs = getMadLibsFromCursor(cursor);
		if (cursor != null)
			cursor.close();
		this.closeDB();
		return madLibs;
	}
	
	//get a MadLibs object from the cursor
	private static MadLibs getMadLibsFromCursor(Cursor cursor){
		if (cursor == null || cursor.getCount() == 0)
			return null;
		else{
			try{
				int id = cursor.getInt(MADLIB_ID_COL);
				String story = cursor.getString(MADLIB_STORY_COL);
				MadLibs madLibs = new MadLibs(id, story);
				return madLibs;
			}
			catch(Exception e){
				return null;
			}
		}
	}
	
	public Long insertMadLibs(MadLibs madLibs){
		ContentValues cv = new ContentValues();
		//cv.put(MADLIB_ID, madLibs.getId());
		cv.put(MADLIB_STORY, madLibs.getStory());
		this.openWritableDB();
		long rowID = db.insert(MADLIB_TABLE, null, cv);
		this.closeDB();
		return rowID;
		
	}
	
	public void dropTable(){
		this.openWritableDB();
		db.delete(MADLIB_TABLE, null, null);
		this.closeDB();
	}
	
	
}
