package com.home.old.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.home.old.model.Book;
import com.home.old.viewmodel.Library;

public class ViewListener implements ActionListener {

	Library library;
	Book currentBook;
	
	public ViewListener(Library library) {
		this.library = library;
		this.currentBook = null;
	}
	
	public void setBook(Book currentBook) {
		this.currentBook = currentBook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		library.openBook(currentBook);
	}

}
