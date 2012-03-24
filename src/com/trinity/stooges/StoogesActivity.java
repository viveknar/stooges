package com.trinity.stooges;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SimpleCursorAdapter;

public class StoogesActivity extends ListActivity {
	private MusicDbAdapter databaseHelper;
	private Cursor playlist_list_cursor, songlistCursor;
	private ContentResolver mContentResolver;
	private Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	private EchoNest echonestHelper;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		databaseHelper = new MusicDbAdapter(this);
		databaseHelper.open();
		mContentResolver = getContentResolver();

		try {
			echonestHelper = new EchoNest();
		} catch (Exception e) {
		}

		Runnable musicScanner = new Runnable() {
			@Override
			public void run() {
				SongEntity songinfo;
				int songCount = songlistCursor.getCount(), counter;
				songlistCursor.moveToFirst();
				while (!songlistCursor.isAfterLast()) {
					String artist = songlistCursor
							.getString(songlistCursor
									.getColumnIndex(android.provider.MediaStore.Audio.AudioColumns.ARTIST));
					String title = songlistCursor
							.getString(songlistCursor
									.getColumnIndex(android.provider.MediaStore.MediaColumns.TITLE));
					songinfo = null;
					try {
						songinfo = echonestHelper.get_song_details(artist,
								title);
					} catch (Exception e) {
					}
					databaseHelper.addTrackInfo(songinfo);
					songlistCursor.moveToNext();
				}
			}
		};

		// check if number of tracks has changed. if so, update database
		int numTracksInDb = databaseHelper.getNumTracks();
		songlistCursor = mContentResolver.query(musicUri, new String[] {
				android.provider.MediaStore.Audio.AudioColumns.ARTIST,
				android.provider.MediaStore.MediaColumns.TITLE },
				"IS_MUSIC<>0", null, null);
		int numTracksInDevice = songlistCursor.getCount();
		if (numTracksInDevice != numTracksInDb) {
			// number of tracks changed. delete all current database info and
			// repopulate
			databaseHelper.deleteAllTrackInfo();
			new Thread(musicScanner).start();
		}

		// display list of playlists in activity
		playlist_list_cursor = databaseHelper.fetchAllPlaylists();
		startManagingCursor(playlist_list_cursor);
		String from[] = new String[] { MusicDbAdapter.playlist_table_namecol };
		int[] to = new int[] { R.id.list_row };
		SimpleCursorAdapter playlistAdapter = new SimpleCursorAdapter(this,
				R.layout.listrow, playlist_list_cursor, from, to);
		setListAdapter(playlistAdapter);
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainactvty_menu, menu);
		return true;
	}
	
}