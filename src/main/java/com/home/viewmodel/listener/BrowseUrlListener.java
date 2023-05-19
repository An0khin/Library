package com.home.viewmodel.listener;

import com.home.model.Book;
import com.home.viewmodel.Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseUrlListener implements ActionListener {

    Library library;
    Book currentBook;

    public BrowseUrlListener(Library library) {
        this.library = library;
        this.currentBook = null;
    }

    public void setBook(Book currentBook) {
        this.currentBook = currentBook;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        library.browseUrl(currentBook.getUrl());
    }

}
