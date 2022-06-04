package com.home.viewmodel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import com.home.model.Book;
import com.home.model.BookList;
import com.home.model.Genres;

public class Library extends Observable{
	
	File directory;
	File genresFile;
	BookList books;
	XMLManager xmlManager;
	
	public Library() {
		directory = new File(System.getProperty("user.dir"));
		xmlManager = new XMLManager(directory);
		
		prepareGenres();
	}
	
	private void prepareGenres() {
		List<String> startGenres = new ArrayList<>(Arrays.asList(new String[] {"", "Fantasy", "Action", "Adventure", "Classics", "Comic", "Detective", "Mystery", "Historical Fiction", 
				"Horror", "Literary Fiction", "Romance", "Science Fiction", "Short Stories", "Thrillers", "Poetry"}));
		
		genresFile = new File(directory + File.separator + "Genres.txt");
		
		if(genresFile.exists()) {
			try(Scanner scan = new Scanner(genresFile)) {
				
				while(scan.hasNext()) {
					startGenres.add(scan.nextLine().trim());
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Genres.addGenresList(startGenres);		
	}
	
	private void checkGenres(String genre) {
		if(!Genres.hasGenre(genre)) {			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(genresFile))) {
				bw.write(genre + "\n");		
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setModel(BookList books) {
		this.books = books;
		this.books.addAll(xmlManager.getList());
		change();
	}
	
	public void change() {
		setChanged();
		notifyObservers();
	}
	
	public List<String> getList() {
		return books.getList();
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
}