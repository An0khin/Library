package com.home.viewmodel;

import com.home.model.Book;

import java.io.File;

public class BookManager {
    static int curId = 0;

    public static Book createBook(String title, String author, String genre, int pages, String description, int rating, File address, String url) {
        return new Book(curId++, title, author, genre, pages, description, rating, address, url);
    }

    public static Book createBook(int id, String title, String author, String genre, int pages, String description, int rating, File address, String url) {
        Book book = new Book(id, title, author, genre, pages, description, rating, address, url);
        curId = id + 1;
        return book;
    }

    public static Book createBook(String title, String author, String genre, String pages, String description, int rating, File address) {
        int pagesInt = 0;

        try {
            pagesInt = Integer.parseInt(pages);
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }

        return createBook(title, author, genre, pagesInt, description, rating, address, "");
    }

    public static Book createBookForReplace(Book book, String title, String author, String genre, String pages, String description, int rating, File address, String url) {
        int pagesInt = 0;

        try {
            pagesInt = Integer.parseInt(pages);
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }

        return new Book(book.getId(), title, author, genre, pagesInt, description, rating, address, url);
    }

    public static int getIdFromRecord(String record) {
        return Integer.parseInt(record.substring(0, record.indexOf(" ")));
    }

    public static void getDeleted() {
        curId--;
    }

    public static String getAddress(File file) {
        String adr = "";
        if(file != null) {
            adr = file.getAbsolutePath();
        }
        return adr;
    }
}
