package com.home.model;

import java.util.ArrayList;
import java.util.List;

public class Genres {
    private static final List<String> GENRES_LIST = new ArrayList<>();
    public static void addGenresList(List<String> genres) {
        for(String genre : genres) {
            if(!hasGenre(genre)) {
                addGenre(genre);
            }
        }
    }
    public static void addGenre(String genre) {
        GENRES_LIST.add(genre);
    }
    public static boolean hasGenre(String genre) {
        return GENRES_LIST.contains(genre);
    }
    public static String[] getArray() {
        return GENRES_LIST.toArray(new String[0]);
    }
}
