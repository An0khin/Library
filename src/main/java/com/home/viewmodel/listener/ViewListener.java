package com.home.viewmodel.listener;

import com.home.model.Book;
import com.home.viewmodel.Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
