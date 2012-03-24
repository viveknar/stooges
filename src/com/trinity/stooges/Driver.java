package com.trinity.stooges;

import java.io.IOException;
import com.echonest.api.v4.EchoNestException;

class Driver {
	public static void main(String[] args) throws IOException, EchoNestException {
		EchoNest e = new EchoNest();
		SongEntity list = e.get_song_details("metallica", "one");
		System.out.println(list.get_danceability()+" "+list.get_duration());
		SongEntity similar = e.get_similar_song("80's heavy metal");
		System.out.println(similar.get_id());
	}
}
