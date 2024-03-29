package com.home.viewmodel.listener;

import com.home.model.Book;
import com.home.model.Genres;
import com.home.viewmodel.BookManager;
import com.home.viewmodel.Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewListener implements ActionListener {

    Library library;

    public NewListener(Library library) {
        this.library = library;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Show window with input areas
        JTextField usernameField = new JTextField();
        JTextField authorField = new JTextField();

        JComboBox<String> genresField = new JComboBox<>(Genres.getArray());
        genresField.setEditable(true);

        JTextField pagesField = new JTextField();

        JTextArea descriptionField = new JTextArea(5, 5);
        JScrollPane descriptionPane = new JScrollPane(descriptionField);
        descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setAutoscrolls(true);

        Integer[] ratingAr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox<Integer> ratingField = new JComboBox<>(ratingAr);

        JLabel selectText = new JLabel();
        FileListener listener = new FileListener(selectText, null);
        JButton button = new JButton("SELECT FILE: ");
        button.addActionListener(listener);

        Object[] message = {"Title:", usernameField, "Author:", authorField, "Genres:", genresField, "Pages:",
                pagesField, "Rating:", ratingField, "Description:", descriptionPane, button, selectText};

        int option = JOptionPane.showConfirmDialog(null, message, "New Book", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if(option == JOptionPane.OK_OPTION) {
            // Creating book by books' creator

            String title = usernameField.getText();
            String author = authorField.getText();
            String genre = (String) genresField.getSelectedItem();
            String pages = pagesField.getText();
            int rating = (int) ratingField.getSelectedItem();
            String description = descriptionField.getText();
            File file = listener.getSelected();

            Book book = BookManager.createBook(title, author, genre, pages, description, rating, file);

            library.addBook(book);

            library.setUrl(book);
        }
    }

}
