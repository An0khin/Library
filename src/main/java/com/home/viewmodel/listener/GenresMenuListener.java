package com.home.viewmodel.listener;

import com.home.model.Views;
import com.home.viewmodel.Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenresMenuListener implements ActionListener {

    Library library;

    public GenresMenuListener(Library library) {
        this.library = library;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        library.setView(Views.GENRES);
    }

}
