package com.home.viewmodel;

import com.home.DefaultXMLManager;
import com.home.XMLManager;
import com.home.model.*;
import com.home.view.LibraryWindow;
import com.home.view.SideViewBook;
import com.home.viewmodel.listener.*;

import javax.xml.transform.TransformerConfigurationException;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {
    private static final String BOOK_XML_CATALOGUE_NAME = "BookCatalogue";
    private static final String GENRE_XML_CATALOGUE_NAME = "Genres";
    private final File xmlFile;
    private final BookList bookList;
    private XMLManager xmlManager;
    private final LibraryWindow libraryWindow;
    private Book selectedBook;
    private EditListener editListener;
    private DeleteListener deleteListener;
    private BrowseUrlListener browseUrlListener;
    private ViewListener viewListener;

    public Library(LibraryWindow libraryWindow) {
        this.bookList = new BookList();
        this.libraryWindow = libraryWindow;
        String directoryPath = System.getProperty("user.home") + "/Documents/Library/";
        File directory = new File(directoryPath);
        directory.mkdir();

        String xmlFilePath = directoryPath + "data.xml";
        this.xmlFile = new File(xmlFilePath);

        try {
            this.xmlManager = new DefaultXMLManager(xmlFilePath);
        } catch(TransformerConfigurationException e) {
            e.printStackTrace();
        }

        setupListeners();
        prepareGenres();
        updateListBook();
    }

    private void prepareGenres() {
        List<String> startGenres = new ArrayList<>(Arrays.asList("", "Fantasy", "Action", "Adventure", "Classics", "Comic", "Detective", "Mystery", "Historical Fiction",
                "Horror", "Literary Fiction", "Romance", "Science Fiction", "Short Stories", "Thrillers", "Poetry"));

        Genres.addGenresList(startGenres);
        List<String> xmlGenres = getGenresFromXml();

        Genres.addGenresList(xmlGenres);
    }

    private List<String> getGenresFromXml() {
        List<String> genres = new ArrayList<>();

        List<String[]> xmlGenres = xmlManager.getListOf(GENRE_XML_CATALOGUE_NAME, "Genre", Genre.getNull());
        for(String[] genreValues : xmlGenres) {
            genres.add(genreValues[0]);
        }

        return genres;
    }

    private void setupListeners() {
        setupMainListeners();
        setupViewMenuListeners();
        setupFileMenuListeners();
        setupListListeners();
    }

    private void setupMainListeners() {
        NewListener newListener = new NewListener(this);
        editListener = new EditListener(this);
        deleteListener = new DeleteListener(this);
        browseUrlListener = new BrowseUrlListener(this);
        viewListener = new ViewListener(this);

        libraryWindow.buttonNew.addActionListener(newListener);
        libraryWindow.buttonEdit.addActionListener(editListener);
        libraryWindow.buttonDelete.addActionListener(deleteListener);
    }

    private void setupViewMenuListeners() {
        BooksMenuListener booksMenuListener = new BooksMenuListener(this);
        AuthorsMenuListener authorsMenuListener = new AuthorsMenuListener(this);
        GenresMenuListener genresMenuListener = new GenresMenuListener(this);

        libraryWindow.booksMenu.addActionListener(booksMenuListener);
        libraryWindow.authorsMenu.addActionListener(authorsMenuListener);
        libraryWindow.genresMenu.addActionListener(genresMenuListener);
    }

    private void setupFileMenuListeners() {
        ImportListener importListener = new ImportListener(this);
        ExportListener exportListener = new ExportListener(this);

        libraryWindow.importXmlMenu.addActionListener(importListener);
        libraryWindow.exportXmlMenu.addActionListener(exportListener);
    }

    private void setupListListeners() {
        SelectBookListener selectBookListener = new SelectBookListener(this);

        libraryWindow.mainList.addMouseListener(selectBookListener);
    }

    private void addGenre(String genre) {
        if(Genres.hasGenre(genre)) {
            return;
        }

        Genres.addGenre(genre);
        xmlManager.addNode(new Genre(genre), "Genre", GENRE_XML_CATALOGUE_NAME);
    }

    private void updateListBook() {
        bookList.clear();
        bookList.addAll(getBooksFromXml());
        updateBooks();
    }

    private List<Book> getBooksFromXml() {
        List<Book> books = new ArrayList<>();

        List<String[]> xmlBooks = xmlManager.getListOf(BOOK_XML_CATALOGUE_NAME, "Book", Book.getNull());
        for(String[] bookValues : xmlBooks) {
            Book book = BookManager.createBook(
                    Integer.parseInt(bookValues[0]),
                    bookValues[1],
                    bookValues[2],
                    bookValues[3],
                    Integer.parseInt(bookValues[4]),
                    bookValues[5],
                    Integer.parseInt(bookValues[6]),
                    bookValues[7].isBlank() ? null : new File(bookValues[7]),
                    bookValues[8]
            );

            books.add(book);
        }

        return books;
    }

    private void updateBooks() {
        this.libraryWindow.mainList.setListData(bookList.getList().toArray(new String[0]));
        this.selectedBook = null;

        updateListeners();

        this.libraryWindow.bookReviewPanel.removeAll();
        this.libraryWindow.bookReviewPanel.updateUI();
    }

    private void updateListeners() {
        editListener.setBook(selectedBook);
        deleteListener.setBook(selectedBook);
        browseUrlListener.setBook(selectedBook);
        viewListener.setBook(selectedBook);
    }

    public void importXml(File xmlFile) {
        xmlManager.loadXml(xmlFile);
        prepareGenres();
        updateListBook();
    }

    public void export(File file) {
        try {
            Files.copy(xmlFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addBook(Book book) {
        addGenre(book.getGenre());

        bookList.addBook(book);
        xmlManager.addNode(book, "Book", BOOK_XML_CATALOGUE_NAME);
        updateBooks();
    }

    public void editBook(Book book, Book toBook) {
        addGenre(toBook.getGenre());

        bookList.editBook(book, toBook);
        xmlManager.editNode(book, toBook, "Book", BOOK_XML_CATALOGUE_NAME);
        updateBooks();
    }

    public Book getBookById(int id) {
        return bookList.getBookById(id);
    }

    public void removeBook(Book book) {
        bookList.removeBook(book);
        BookManager.getDeleted();
        xmlManager.removeNode(book, "Book", BOOK_XML_CATALOGUE_NAME);
        updateXml();
        updateBooks();
    }

    private void updateXml() {
        xmlManager.clear(BOOK_XML_CATALOGUE_NAME);
        for(Book book : bookList.getBooks()) {
            xmlManager.addNode(book, "Book", BOOK_XML_CATALOGUE_NAME);
        }
    }

    public void openBook(Book book) {
        try {
            Desktop.getDesktop().open(book.getAddress());
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
        Thread thread = new Thread(new UrlFinder(this, book));
        thread.start();
    }

    public void setView(Views view) {
        switch(view) {
            case BOOKS -> updateListBook();
            case AUTHORS -> {
                bookList.clear();
                bookList.addAll(getBooksFromXml());
                bookList.sortByAuthor();
            }
            case GENRES -> {
                bookList.clear();
                bookList.addAll(getBooksFromXml());
                bookList.sortByGenre();
            }
        }
        updateBooks();
    }

    public void updateSelectedBook() {
        if(libraryWindow.mainList.isSelectionEmpty()) {
            return;
        }

        String record = libraryWindow.mainList.getSelectedValue();

        this.selectedBook = getBookById(BookManager.getIdFromRecord(record));

        updateListeners();

        viewBook();
    }

    private void viewBook() {
        SideViewBook sideViewBook = new SideViewBook(
                libraryWindow.bookReviewPanel,
                viewListener,
                browseUrlListener,
                this.selectedBook
        );

        libraryWindow.validate();
    }
}
