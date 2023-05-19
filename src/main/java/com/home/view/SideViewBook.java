package com.home.view;

import com.home.model.Book;
import com.home.viewmodel.BookManager;
import com.home.viewmodel.listener.BrowseUrlListener;
import com.home.viewmodel.listener.ViewListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class SideViewBook {
    private final JPanel panel;
    private final ViewListener viewListener;
    private final BrowseUrlListener browseUrlListener;
    private final Book book;

    public SideViewBook(JPanel panel, ViewListener viewListener, BrowseUrlListener browseUrlListener, Book book) {
        this.panel = panel;
        this.viewListener = viewListener;
        this.browseUrlListener = browseUrlListener;
        this.book = book;

        clearPanel();
        show();
    }

    private void clearPanel() {
        panel.removeAll();
        panel.updateUI();
    }

    private void show() {
        JTextComponent[] jTextComponents = setupJTextComponents();
        JScrollPane descriptionPane = setupDescriptionPane();

        JTextField nameFile = setupNameFileField();
        JButton viewButton = setupViewBookButton();
        JTextField nameUrl = setupNameUrlField();
        JButton browseUrlButton = setupBrowseUrlButton();


        for(JTextComponent comp : jTextComponents) {
            comp.setEditable(false);
            panel.add(comp);
        }

        panel.add(descriptionPane);

        if(nameFile.getText().length() > 6) {
            panel.add(nameFile);
            panel.add(viewButton);
        }

        if(nameUrl.getText().length() > 5) {
            panel.add(nameUrl);
            panel.add(browseUrlButton);
        }
    }

    private JTextComponent[] setupJTextComponents() {
        JTextField idField = new JTextField("Id: " + book.getId());
        JTextField usernameField = new JTextField("Title: " + book.getTitle());
        JTextField authorField = new JTextField("Author: " + book.getAuthor());
        JTextField genresField = new JTextField("Genre: " + book.getGenre());
        JTextField pagesField = new JTextField("Pages: " + book.getPages());
        JTextField ratingField = new JTextField("Rating: " + book.getRating());

        return new JTextComponent[] {
                idField,
                usernameField,
                authorField,
                genresField,
                pagesField,
                ratingField,
        };
    }

    private JScrollPane setupDescriptionPane() {
        JTextArea descriptionArea = setupDescriptionArea();

        JScrollPane descriptionPane = new JScrollPane(descriptionArea);
        descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setAutoscrolls(true);

        return descriptionPane;
    }

    private JTextArea setupDescriptionArea() {
        JTextArea descriptionArea = new JTextArea("Description: " + book.getDescription(), 5, 5);
        descriptionArea.setEditable(false);

        return descriptionArea;
    }

    private JTextField setupNameFileField() {
        JTextField nameFile = new JTextField("File: " + BookManager.getAddress(book.getAddress()));
        nameFile.setEditable(false);

        return nameFile;
    }

    private JButton setupViewBookButton() {
        JButton viewButton = new JButton("VIEW");
        viewButton.addActionListener(viewListener);

        return viewButton;
    }

    private JTextField setupNameUrlField() {
        JTextField nameUrl = new JTextField("URL: " + book.getUrl());
        nameUrl.setEditable(false);

        return nameUrl;
    }

    private JButton setupBrowseUrlButton() {
        JButton browseUrlButton = new JButton("BROWSE URL");
        browseUrlButton.addActionListener(browseUrlListener);

        return browseUrlButton;
    }
}
