package com.home.model;

import java.util.ArrayList;
import java.util.List;

public class BookList {
	List<Book> books = new ArrayList<>();
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public Book getBookById(int id) {
		for(Book book : books) {
			if(book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	
	public List<String> getList() {
		List<String> tmpList = new ArrayList<String>();
		for(Book book : books) {
			tmpList.add(book.getId() + " - " + book.getTitle());
		}
		return tmpList;
	}
	
	public void editBook(Book book) {
		//
	}
}
