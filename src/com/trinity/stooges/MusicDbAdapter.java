package com.trinity.stooges;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDbAdapter {
	private class column 
	{
		public String name;
		public String type;
		public column(String nm, String typ)
		{
			name = nm;
			type = typ;
		}
	}

public static final String music_data_database = "music_data";
private static final int music_data_database_version = 1;
public static final String playlist_table = "playlists";
public static final String playlist_table_namecol = "name";
public static final String tracks_table = "tracks";
public static final String playlist_table_create_string = "create table " + playlist_table
					+ " (_id integer primary key autoincrement, name text not null, numsongs integer not null, songlisttablename text);";
public static final String tracks_table_create_string = "create table " + tracks_table 
							+ " (_id integer primary key autoincrement, name text not null, artist text, energy float, tempo float, danceability float, duration float, songid text);";
private MusicDbHelper musicDbHelper;
private Context mContext;
private SQLiteDatabase musicDatabase;

public MusicDbAdapter(Context context) {
	mContext = context;
}

private static class MusicDbHelper extends SQLiteOpenHelper
{
public MusicDbHelper(Context context) {
	super(context, music_data_database, null, music_data_database_version);
}

@Override
public void onCreate(SQLiteDatabase db) {
	// TODO create tables here
	// create: playlist table, tracks table
	db.execSQL(playlist_table_create_string);
	db.execSQL(tracks_table_create_string);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO upgrade database to newer version	
}
}

public void open()
{
	musicDbHelper = new MusicDbHelper(mContext);
	musicDatabase = musicDbHelper.getWritableDatabase();
}

public void close()
{ musicDbHelper.close(); }

public Cursor fetchAllPlaylists()
{
	return musicDatabase.query(playlist_table, new String[] {playlist_table_namecol}, null, null, null, null, null); 
}

/*public SongEntity[] fetchAllSongs()
{
	
}*/

}
