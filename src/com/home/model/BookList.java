package com.home.model;

import java.util.ArrayList;
import java.util.List;

import com.home.viewmodel.BookManager;

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
	
	public void removeBook(Book book) {
		BookManager.getDeleted();
		
		boolean findForDel = false;
		
		for(Book tmpBook : books) {
			if(findForDel) {
				tmpBook.setId(tmpBook.getId() - 1);
			} else {
				if(tmpBook.getId() == book.getId()) {
					findForDel = true;
				}
			}
		}
		
		books.remove(book);
	}
}
