package com.trinity.stooges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class EchoNest {
	
	public static String ANALYZE_TRACK_URL = "http://developer.echonest.com/api/v4/track/profile?api_key=N6E4NIOVYMTHNDM8J&format=json&bucket=audio_summary&";
	public static String SONG_SEARCH_URL = "http://developer.echonest.com/api/v4/song/search?api_key=N6E4NIOVYMTHNDM8J&format=json&results=3&bucket=id:7digital-US&bucket=audio_summary&bucket=tracks&";
	public static String SIMILAR_SEARCH_URL = "http://developer.echonest.com/api/v4/song/search?api_key=N6E4NIOVYMTHNDM8J&format=json&results=1&";
	
	private JSONObject return_response(String link) throws IOException, JSONException {
		
		URL url = new URL(link);
		InputStream in = url.openStream();
		String response = "";
		JSONObject jobj = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		for (String line; (line = reader.readLine()) != null;) {
		    response += line;
		}
		in.close();
		jobj = new JSONObject(response);
		return jobj;
		
	}
	
	public SongEntity get_song_details(String artist, String title) throws IOException, JSONException {
		@SuppressWarnings("deprecation")
		String link = SONG_SEARCH_URL+"artist="+URLEncoder.encode(artist)+"&title="+URLEncoder.encode(title);
		System.out.println(link);
		JSONObject response = return_response(link);		
		response = response.getJSONObject("response");
		SongEntity song = new SongEntity();
		JSONArray songs = response.getJSONArray("songs");
		for (int i = 0;i < songs.length();i++) {
			JSONObject c = songs.getJSONObject(i);
			song.set_artist(c.getString("artist_name"));
			song.set_title(c.getString("title"));
			try {
				JSONArray tracks = c.getJSONArray("tracks");
				song.set_id(tracks.getJSONObject(0).getString("id"));
			} catch (JSONException e) {
				// TODO: handle exception
			}
		}		
		song = get_metadata(song);
		return song;
	}
	
	private SongEntity get_metadata(SongEntity song) throws IOException, JSONException {		
		if(song.get_id()!="")
		{
		@SuppressWarnings("deprecation")
		String link = ANALYZE_TRACK_URL+"id="+URLEncoder.encode(song.get_id());
		System.out.println(link);
		JSONObject response = return_response(link);
		response = response.getJSONObject("response");
		song.set_danceability(new Float(response.getJSONObject("track").getJSONObject("audio_summary").getLong("danceability")));
		song.set_duration(new Float(response.getJSONObject("track").getJSONObject("audio_summary").getLong("duration")));
		song.set_energy(new Float(response.getJSONObject("track").getJSONObject("audio_summary").getLong("energy")));
		song.set_tempo(new Float(response.getJSONObject("track").getJSONObject("audio_summary").getLong("tempo")));
		}
		return song;
	}
	
	@SuppressWarnings("deprecation")
	public SongEntity get_similar_song(String description) throws IOException, JSONException {
		String[] words = description.split(" ");
		String params = "";
		for (String word : words) {
			word = word.replaceAll("[!@#$%^&*()-_,.]", "");
			params += "description="+URLEncoder.encode(word)+"&";
		}
		
		String link = SONG_SEARCH_URL+params;
		System.out.println(link);
		JSONObject response = return_response(link);
		response = response.getJSONObject("response");
		SongEntity song = new SongEntity();
		JSONArray songs = response.getJSONArray("songs");
		for (int i = 0;i < songs.length();i++) {
			JSONObject c = songs.getJSONObject(i);
			song.set_artist(c.getString("artist_name"));
			song.set_title(c.getString("title"));
			try {
				JSONArray tracks = c.getJSONArray("tracks");
				song.set_id(tracks.getJSONObject(0).getString("id"));
			} catch (JSONException e) {
				// TODO: handle exception
			}
		}
		song = get_metadata(song);
		return song;
		
	}
}