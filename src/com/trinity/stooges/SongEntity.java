package com.trinity.stooges;

class SongEntity {
	String id, artist, title;
	float energy, tempo, danceability, duration;
	
	public SongEntity() {
		id = ""; artist = ""; title = "";
		energy = 0.0F; tempo = 0.0F; danceability = 0.0F; duration = 0.0F;
	}
	
	public String get_id() {
		return id;
	}
	public void set_id(String name) {
		id = name;
	}
	
	public String get_artist() {
		return artist;
	}
	public void set_artist(String name) {
		artist = name;
	}
	
	public String get_title() {
		return title;
	}
	public void set_title(String name) {
		title = name;
	}
	
	public float get_energy() {
		return energy;
	}
	public void set_energy(float value) {
		
	}
	
	public float get_tempo() {
		return tempo;
	}
	
	public void set_tempo(float value) {
		tempo = value;
	}
	
	public float get_danceability() {
		return danceability;
	}
	public void set_danceability(float value) {
		danceability = value;
	}
	
	public float get_duration() {
		return duration;
	}
	public void set_duration(float value) {
		duration = value;
	}
	
}