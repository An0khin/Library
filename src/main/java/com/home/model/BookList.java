package com.home.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookList {
    private List<Book> books;

    public BookList() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addAll(List<Book> bookList) {
        books.addAll(bookList);
    }

    public Book getBookById(int id) {
        for(Book book : books) {
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<String> getList() {
        List<String> resultList = new ArrayList<>();
        for(Book book : books) {
            resultList.add(book.getId() + " - " + book.getTitle());
        }
        return resultList;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public void editBook(Book book, Book toBook) {
        int index = -1;
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == book.getId()) {
                index = i;
                break;
            }
        }
        if(index != -1) {
            books.set(index, toBook);
        }
    }

    public void removeBook(Book book) {
        boolean isFoundForDelete = false;

        for(Book tmpBook : books) {
            if(isFoundForDelete) {
                tmpBook.setId(tmpBook.getId() - 1);
            } else {
                if(tmpBook.getId() == book.getId()) {
                    isFoundForDelete = true;
                }
            }
        }

        books.remove(book);
    }

    public void clear() {
        books.clear();
    }

    public void sortByAuthor() {
        Set<String> authors = new HashSet<>();
        List<Book> resultList = new ArrayList<>();

        for(Book book : books) {
            authors.add(book.getAuthor());
        }

        for(String author : authors) {
            for(Book book : books) {
                if(author.equals(book.getAuthor())) {
                    resultList.add(book);
                }
            }
        }

        books = resultList;
    }

    public void sortByGenre() {
        Set<String> genres = new HashSet<>();
        List<Book> resultList = new ArrayList<>();

        for(Book book : books) {
            genres.add(book.getGenre());
        }

        for(String genre : genres) {
            for(Book book : books) {
                if(genre.equals(book.getGenre())) {
                    resultList.add(book);
                }
            }
        }

        books = resultList;
    }
}
