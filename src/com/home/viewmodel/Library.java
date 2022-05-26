package com.home.viewmodel;

import java.util.List;
import java.util.Observable;

import com.home.model.BookList;

public class Library extends Observable{
	
	BookList books;
	
	public void setModel(BookList books) {
		this.books = books;
		setChanged();
		notifyObservers();
	}
	
	public void getTest() {
		System.out.println("Yes");
	}
	
	public List<String> getList() {
		return books.getList();
	}
}