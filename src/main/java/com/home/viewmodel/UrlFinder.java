package com.home.viewmodel;

import com.home.model.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlFinder implements Runnable {
    Library library;
    Book book;

    UrlFinder(Library library, Book book) {
        this.library = library;
        this.book = book;
    }

    @Override
    public void run() {
        String result = "";

        try {
            String urlPattern = "https://searx.be/search?q=keyword";
            String url = urlPattern.replace("keyword", URLEncoder.encode(book.getTitle() + " " + book.getAuthor() + " читать", StandardCharsets.UTF_8));
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";

            Document doc = Jsoup.connect(url).userAgent(userAgent).get();

            Elements links = doc.select("h4");

            result = links.select("a").get(0).attr("href");

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        if(!result.isEmpty() && !result.equals(book.getUrl())) {
            library.editBook(book, BookManager.createBookForReplace(book, book.getTitle(), book.getAuthor(), book.getGenre(),
                    Integer.toString(book.getPages()), book.getDescription(), book.getRating(), book.getAddress(), result));
        }
    }
}
