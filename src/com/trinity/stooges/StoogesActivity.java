package com.trinity.stooges;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class StoogesActivity extends ListActivity {
	private MusicDbAdapter databaseHelper;
	private Cursor playlist_list_cursor;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
       databaseHelper = new MusicDbAdapter(this);
       databaseHelper.open();
        
       playlist_list_cursor = databaseHelper.fetchAllPlaylists();
       startManagingCursor(playlist_list_cursor);
       
       String from[] = new String[] {MusicDbAdapter.playlist_table_namecol};
       int[] to = new int[] {R.id.list_row};
   	   SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.listrow, playlist_list_cursor, from, to);
   	   setListAdapter(notes);
    }
    
}