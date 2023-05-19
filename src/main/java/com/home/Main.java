package com.home;

import com.home.view.LibraryWindow;
import com.home.viewmodel.Library;

public class Main {
    public static void main(String[] args) {
        LibraryWindow libraryWindow = new LibraryWindow();
        Library library = new Library(libraryWindow);
    }
}
