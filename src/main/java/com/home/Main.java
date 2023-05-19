package com.home;

import com.home.model.BookList;
import com.home.view.LibraryWindow;
import com.home.viewmodel.Library;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		LibraryWindow window = new LibraryWindow(library);
		
		BookList books = new BookList();

		library.setModel(books);
	}
}