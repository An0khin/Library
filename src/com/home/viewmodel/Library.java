package com.home.viewmodel;

import java.awt.Desktop;
import java.io.File;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import com.home.model.Book;
import com.home.model.BookList;
import com.home.model.Genres;

public class Library extends Observable {
	
	File directory;
	File genresFile;
	BookList books;
	XMLManager xmlManager;
	
	public Library() {
		directory = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library");
		directory.mkdir();
		try {
			Thread.sleep(1);
		} catch(Exception e) {e.printStackTrace();}
		
		directory = new File(directory.getPath() + File.separator + "data.xml");
		
		xmlManager = new XMLManager(directory);
		
		prepareGenres();
	}
	
	private void prepareGenres() {
		List<String> startGenres = new ArrayList<>(Arrays.asList(new String[] {"", "Fantasy", "Action", "Adventure", "Classics", "Comic", "Detective", "Mystery", "Historical Fiction", 
				"Horror", "Literary Fiction", "Romance", "Science Fiction", "Short Stories", "Thrillers", "Poetry"}));
		
		Genres.addGenresList(startGenres);
		Genres.addGenresList(xmlManager.getGenresList());
	}
	
	private void checkGenres(String genre) {
		if(!Genres.hasGenre(genre)) {
			Genres.addGenre(genre);
			xmlManager.addGenre(genre);
		}
	}
	
	private void setListBook() {
		books.clear();
		books.addAll(xmlManager.getListBook());
		change();
	}
	
	public void setModel(BookList books) {
		this.books = books;
		setListBook();
	}
	
	public void change() {
		setChanged();
		notifyObservers();
	}
	
	public List<String> getList() {
		return books.getList();
	}
	
	public void mergeXML(File xml) {
		xmlManager.mergeXML(xml);
		prepareGenres();
		setListBook();
	}

	public void export(File file) {
		try {
			Files.copy(directory.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void addBook(Book book) {
		checkGenres(book.getGenre());
		
		books.addBook(book);		
		xmlManager.addBook(book);
		change();
	}
	
	public void editBook(Book book, Book toBook) {
		checkGenres(toBook.getGenre());
		
		books.editBook(book, toBook);
		xmlManager.editBook(toBook);
		change();
	}
	
	public Book getBookById(int id) {
		return books.getBookById(id);
	}
	
	public void removeBook(Book book) {
		books.removeBook(book);
		xmlManager.removeBook(book);
		change();
	}

	public void openBook(Book book) { 
		try {
			Desktop.getDesktop().open(book.getFile());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void browseUrl(String url) {
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setUrl(Book book) {
		Thread thr = new Thread(new UrlFinder(this, book));
		thr.start();
	}
	
	public void setView(Views view) {
		switch (view) {
			case BOOKS:
				setListBook();
				break;
				
			case AUTHORS:
				books.clear();
				books.addAll(xmlManager.getListBook());
				books.sortByAuthor();
				break;
				
			case GENRES:
				books.clear();
				books.addAll(xmlManager.getListBook());
				books.sortByGenre();
				break;
		}
		change();
	}
}