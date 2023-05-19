package com.home.model;

import com.home.Nodeable;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Book implements Nodeable {
    private int id;
    private final String title;
    private final String author;
    private final String genre;
    private final int pages;
    private final String description;
    private final int rating;
    private final File address;
    private final String url;

    public Book(int id, String title, String author, String genre, int pages, String description, int rating, File address, String url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.url = url;
    }

    public static Book getNull() {
        return new Book(0, null, null, null, 0, null, 0, null, null);
    }

    @Override
    public Map<String, String> getValues() {
        LinkedHashMap<String, String> values = new LinkedHashMap<>();

        values.put("title", title);
        values.put("author", author);
        values.put("genre", genre);
        values.put("pages", String.valueOf(pages));
        values.put("description", description);
        values.put("rating", String.valueOf(rating));
        values.put("address", address == null ? "" : address.getAbsolutePath());
        values.put("url", url);

        return values;
    }

    @Override
    public String[] getIdFieldValue() {
        return new String[] {"id", String.valueOf(id)};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public File getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }
}
