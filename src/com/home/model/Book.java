package com.home.model;

import java.io.File;
import java.net.URL;

public class Book{
	private int id;
	private String title;
	private String author;
	private String genre;
	private int pages;
	private String description;
	private int rating;
	private File address;
	private String url;
	
	public Book(int id, String title, String author, String genre, int pages, String description, int rating, File address, String url) {
		super();
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
	
	//Getters and Setters	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public File getFile() {
		return address;
	}
	
	public void setFile(File address) {
		this.address = address;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
