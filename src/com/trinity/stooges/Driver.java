package com.trinity.stooges;

import java.io.IOException;
import java.util.ArrayList;

import com.echonest.api.v4.EchoNestException;

class Driver {
	
	public static SongEntity blah(SongEntity s, String id, String a, String t, float dance, float tempo, float duration, float energy) {
		s.set_artist(a);
		s.set_title(t);
		s.set_id(id);
		s.set_danceability(dance);
		s.set_duration(duration);
		s.set_tempo(tempo);
		s.set_energy(energy);
		return s;
	}
	
	
	public static void main(String[] args) throws IOException, EchoNestException {
		EchoNest e = new EchoNest();
		SongEntity e1 = new SongEntity();
		SongEntity e2 = new SongEntity();
		SongEntity e3 = new SongEntity();
		SongEntity e4 = new SongEntity();
		SongEntity e5 = new SongEntity();
		SongEntity e6 = new SongEntity();
		SongEntity e7 = new SongEntity();
		SongEntity e8 = new SongEntity();
		SongEntity e9 = new SongEntity();
		SongEntity e10 = new SongEntity();
		SongEntity e11 = new SongEntity();
		
		
		e1 = blah(e1, "asdfd", "metallica", "one", 0.5431F, 123.0F, 321.0F, 0.432F);
		e2 = blah(e2, "fsdsf", "audioslave", "victor", 0.1234F, 34.0F, 23.5F, 0.783F);
		e3 = blah(e3, "ojdsg", "qoigd", "lkgdfg", 0.3456F, 53.0F, 986F, 0.365F);
		e4 = blah(e4, "kpojg", "fsdfg", "one", 0.5231F, 23.0F, 11.0F, 0.5432F);
		e5 = blah(e5, "mvdls", "boasbs", "one", 0.031F, 123.3F, 321.0F, 0.4908F);
		e6 = blah(e6, "werty", "rtyui", "one", 0.5031F, 723.0F, 921.0F, 0.432F);
		e7 = blah(e7, "tyuio", "yuiop", "one", 0.1F, 1.2F, 1.0F, 0.9432F);
		e8 = blah(e8, "asdfg", "sdfgh", "one", 0.398F, 45.23F, 12.23F, 0.01F);
		e9 = blah(e9, "dfghj", "jhffd", "one", 0.234F, 75.0F, 21.0F, 0.632F);
		e10 = blah(e10, "zxcvb", "xcvbn", "one", 0.331F, 221.3F, 51.6F, 0.732F);
		e11 = blah(e11, "bgdrs", "bvcxxz", "one", 0.5456F, 109F, 111.0F, 0.432F);
		
		SongEntity list = e.get_song_details("metallica", "one");
		System.out.println(list.get_danceability()+" "+list.get_duration());
		SongEntity similar = e.get_similar_song("80's heavy metal");
		System.out.println(similar.get_id());
		
		KNN k = new KNN();
		SongEntity[] array = {e1, e2, e3, e4, e5}; 
		ArrayList<String> ids = k.closestNeighbour(e11, array, 3);
		for (String id : ids) {
			System.out.println(id);
		}
	}
	

}
