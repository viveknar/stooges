package com.trinity.stooges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

class KNN {
	
	public ArrayList<String> closestNeighbour(SongEntity seed_song, SongEntity[] songs, int number) {
	    HashMap<String,Float> map = new HashMap<String,Float>();
	    ValueComparator bvc =  new ValueComparator(map);
	    TreeMap<String,Float> sorted_map = new TreeMap<String, Float>(bvc);
		ArrayList<String> distances = new ArrayList<String>();
		for (int i = 0; i < songs.length; i++) {
			 map.put(songs[i].get_id(), (float) computeDistance(seed_song, songs[i]));
		}
		sorted_map.putAll(map);
//		sorted_map.descendingMap();
		int count = 0;
		for (String key :sorted_map.keySet()) {
			if(count >= (songs.length - number)) {
				distances.add(key);
			}
			count++;
		}
		return distances;
	}
	
	private float computeDistance(SongEntity song1, SongEntity song2) {
		float distance = 0.0F;
		float[] params1 = {song1.get_danceability(), song1.get_duration(), song1.get_energy(), song1.get_tempo()};
		float[] params2 = {song2.get_danceability(), song2.get_duration(), song2.get_energy(), song2.get_tempo()};
		
		float sum_of_squares = 0.0F;
		for (int i = 0 ; i < params1.length;i++) {
			sum_of_squares += Math.pow((params1[i] -params2[i]),2); 
		}
		distance = (float) Math.sqrt(sum_of_squares);
		return distance;
	}
}