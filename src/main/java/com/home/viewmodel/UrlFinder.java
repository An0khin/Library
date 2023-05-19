package com.home.viewmodel;

import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.home.model.Book;

public class UrlFinder implements Runnable {

	Library libr;
	Book book;
	
	UrlFinder(Library libr, Book book) {
		this.libr = libr;
		this.book = book;
	}
	
	@Override
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
		
		if(!result.isEmpty() && result != book.getUrl()) {
			libr.editBook(book, BookManager.createBookForReplace(book, book.getTitle(), book.getAuthor(), book.getGenre(), 
					Integer.toString(book.getPages()), book.getRating(), book.getDescription(), book.getFile(), result));
		
			System.out.println("Done");
		}
	}

}
