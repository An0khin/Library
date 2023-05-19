package com.home.model;

import com.home.Nodeable;

import java.util.Collections;
import java.util.Map;

public class Genre implements Nodeable {
    private final String title;

    public Genre(String title) {
        this.title = title;
    }

    public static Genre getNull() {
        return new Genre(null);
    }

    @Override
    public Map<String, String> getValues() {
        return Collections.emptyMap();
    }

    @Override
    public String[] getIdFieldValue() {
        return new String[] {"title", title};
    }
}
