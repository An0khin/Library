package com.home.viewmodel;

import java.io.File;
import java.util.List;
import java.util.Observable;

import com.home.model.Book;
import com.home.model.BookList;

public class Library extends Observable{
	
	File directory;
	BookList books;
	XMLManager xmlManager;
	
	public Library() {
		directory = new File(System.getProperty("user.dir"));
		xmlManager = new XMLManager(directory);
	}
	
	public void setModel(BookList books) {
		this.books = books;
		this.books.addAll(xmlManager.getList());
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
		xmlManager.addBook(book);
		change();
	}
	
	public void editBook(Book book, Book toBook) {
		books.editBook(book, toBook);
		xmlManager.editBook(toBook);
		change();
	}
	
	public Book getBookById(int id) {
		return books.getBookById(id);
	}
	
	public void removeBook(Book book) {
		books.removeBook(book);
		xmlManager.removeBook(book);
		change();
	}
}