package com.home.viewmodel;

import com.home.model.Book;
import com.home.model.Genres;

public class BookManager {
	static int curId = 0;
	
	public static Book createBook(String title, String author, Genres genre, int pages, String description, int rate) {
		Book book = new Book(curId++, title, author, genre, pages, description, rate);
	
		return book;
	}
	
	public static int getIdFromRecord(String record) {
		return Integer.parseInt(record.substring(0, record.indexOf(" ")));
	}
}
