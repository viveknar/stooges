package com.trinity.stooges;

import java.util.List;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import de.umass.lastfm.*;
import java.lang.Object;

import com.echonest.api.v4.EchoNestException;

class Driver {
	public static void main(String[] args) throws IOException, EchoNestException {
		EchoNest e = new EchoNest();
		SongEntity list = e.get_song_details("metallica", "one");
		System.out.println(list.get_danceability()+" "+list.get_duration());
		SongEntity similar = e.get_similar_song("80's heavy metal");
		System.out.println(similar.get_artist());
	}
}
