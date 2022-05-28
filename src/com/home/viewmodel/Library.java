package com.home.viewmodel;

import java.util.List;
import java.util.Observable;

import com.home.model.Book;
import com.home.model.BookList;

public class Library extends Observable{
	
	BookList books;
	
	public void setModel(BookList books) {
		this.books = books;
		change();
	}
	
	public void change() {
		setChanged();
		notifyObservers();
	}
	
	public List<String> getList() {
		return books.getList();
	}
	
	public void addBook(Book book) {
		books.addBook(book);
		change();
	}
	
	public Book getBookById(int id) {
		return books.getBookById(id);
	}
}