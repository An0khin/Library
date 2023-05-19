package com.home.viewmodel.listener;

import com.home.viewmodel.Library;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectBookListener extends MouseAdapter {
    private final Library library;

    public SelectBookListener(Library library) {
        this.library = library;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            library.updateSelectedBook();
        }
        super.mouseClicked(e);
    }
}
