package com.home.viewmodel;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
	
	public void openBook(Book book) { 
		try {
			Desktop.getDesktop().open(book.getFile());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String getUrl(String title, String author) {
		String result = "";
		String start = "https://www.google.com/search?q=";
		start = "https://yandex.ru/search/?text=";
		boolean hasLink = false;
		
		try {
			String parseLine;
			
			String q = title + " " + author;
			q = q.trim().replaceAll(" ", "+") + "+читать";
			System.out.println(URLEncoder.encode(q, "UTF-8"));
			URL url = new URL(start + URLEncoder.encode(q, "UTF-8"));
			
			//url = new URL("https://yandex.ru/search/?lr=54&text=%D0%A1%D0%B2%D0%B0%D1%80%D0%BE%D0%B3.+%D0%9F%D0%BE%D0%B1%D0%B5%D0%B4%D0%B8%D1%82%D0%B5%D0%BB%D1%8C+%D0%B7%D0%BB%D0%B0");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            while(hasLink == false) {
            	if((parseLine = br.readLine()) != null) {
//            		System.out.println(parseLine);
//            		Link Link_theme_normal OrganicTitle-Link organic__url link i-bem
            		if(parseLine.contains("class=\"Link Link_theme_normal OrganicTitle-Link organic__url link\"")) {
//            			System.out.println(parseLine);
            			hasLink = true;
            			int firstIndex = parseLine.indexOf("href=\"") + 6;
            			String firstPart = parseLine.substring(firstIndex);
            			result = firstPart.substring(0, firstPart.indexOf("\""));
            			break;
            		}
            	} else {
            		break;
            	}
            }
            br.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
//		if(hasLink)
//			result = start + result;
		
		System.out.println(result);
		
		return result;
	}
	
	public void browseUrl(String url) {
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}