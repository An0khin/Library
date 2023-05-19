package com.home.old;

import com.home.old.model.BookList;
import com.home.old.view.LibraryWindow;
import com.home.old.viewmodel.Library;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		LibraryWindow window = new LibraryWindow(library);
		
		BookList books = new BookList();

		library.setModel(books);
	}
}