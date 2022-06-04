package com.home.model;

import java.util.ArrayList;
import java.util.List;

public class Genres {	
	
	private static List<String> genresList = new ArrayList<>();
	
	public static void addGenresList(List<String> genres) {
		genresList.addAll(genres);
	}
		
	public static void addGenre(String genre) {
		Genres.genresList.add(genre);
	}
	
	public static boolean hasGenre(String genre) {		
		return Genres.genresList.indexOf(genre) != -1 ? true : false;
	}
	
	public static String[] getArray() {
		return Genres.genresList.toArray(new String[0]);
	}
}
