package com.trinity.stooges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.management.Descriptor;

import com.echonest.api.v4.*;
import com.echonest.api.v4.PlaylistParams.PlaylistType;

class EchoNest {
	private EchoNestAPI en;
	
		public EchoNest() throws EchoNestException {
			en = new EchoNestAPI("FQTGFAEJK1DLGZVAL");
		    en.setTraceSends(false);
		    en.setTraceRecvs(false);	
		}
	
		public SongEntity get_song_details(String artist, String title) throws EchoNestException {
			SongParams params = new SongParams();
			params.setArtist(artist);
			params.setTitle(title);
			params.setResults(1);
			List<Song> songs = en.searchSongs(params);
			return return_songs_features(songs);
		}
		
		public SongEntity get_similar_song(String description) throws EchoNestException {
			PlaylistParams params = new PlaylistParams();
			String[] keys = description.split(" ");
			for (String key : keys) {
				params.addDescription(key);
			}
			params.setType(PlaylistType.ARTIST_DESCRIPTION);
			params.setResults(1);
			Playlist playlist = en.createStaticPlaylist(params);
			List<Song> songs = playlist.getSongs();
			return return_songs_features(songs);
		}
		
		
		
		private SongEntity return_songs_features(List<Song> songs) throws EchoNestException {
			SongEntity s = new SongEntity();
			for (Song song : songs) {
				s.set_artist(song.getArtistName());
				s.set_title(song.getTitle());
				s.set_energy((float)song.getEnergy());
				s.set_tempo((float) song.getTempo());
				s.set_danceability((float) song.getDanceability());
				s.set_duration((float) song.getDuration());
			}
			return s;
		}
}