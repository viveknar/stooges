package com.trinity.stooges;

class SongEntity {
	String Id, artist, title;
	float energy, tempo, danceability, duration;
	
	public String get_id() {
		return Id;
	}
	public void set_id(String name) {
		Id = name;
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