package com.home.viewmodel;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

	public void openBook(Book book) { 
		try {
			Desktop.getDesktop().open(book.getFile());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void browseUrl(String url) {
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setUrl(Book book) {
		Thread thr = new Thread(new UrlFinder(book));
		thr.start();
	}
	
	private class UrlFinder implements Runnable {
		Book book;
		
		UrlFinder(Book book) {
			this.book = book;
		}
		
		public void run() {
			String result = "";
			
			try {
				String urlPattern = "https://searx.be/search?q=keyword";
				String url = urlPattern.replace("keyword", URLEncoder.encode(book.getTitle() + " " + book.getAuthor() + " читать", "utf-8"));
				String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
				
				Document doc = Jsoup.connect(url).userAgent(userAgent).get();
				
				Elements links = doc.select("h4");
				
				result = links.select("a").get(0).attr("href");
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			editBook(book, BookManager.createBookForReplace(book, book.getTitle(), book.getAuthor(), book.getGenre(), 
					Integer.toString(book.getPages()), book.getRating(), book.getDescription(), book.getFile(), result));
			
			System.out.println("Done");
		}
	}
}