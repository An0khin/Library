package com.home.viewmodel.listener;

import com.home.model.Book;
import com.home.viewmodel.Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteListener implements ActionListener {

    Library library;
    Book currentBook;

    public DeleteListener(Library library) {
        this.library = library;
        this.currentBook = null;
    }

    public void setBook(Book currentBook) {
        this.currentBook = currentBook;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int option = JOptionPane.showConfirmDialog(null, "You really wanna delete?");
        if(option == JOptionPane.OK_OPTION) {
            if(currentBook != null) {
                library.removeBook(currentBook);
            }
        }
    }
}
