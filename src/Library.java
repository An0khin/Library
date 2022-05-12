import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import windows.LibraryWindow;

public class Library {
	private StateView stateView;
	private List<Author> authorsList = new ArrayList<>(); //Will be replaced by database
	private List<Book> booksList = new ArrayList<>(); //Will be replaced by database
	private LibraryWindow window;
	private Listeners listeners;
	private CreatorBooks creatorBooks;
	
	
	public static void main(String[] args) {
		Library libr = new Library();
		libr.start();		
	}
	
	public void start() {
		window = LibraryWindow.getWindow(); //Will be library's gui
		//NewActionListener newListener = Listeners.NewActionListener();
		
		creatorBooks = new CreatorBooks(booksList);
		
		this.listeners = new Listeners(this);
		window.setButNewListener(listeners.getNewActionListener());		
	}
	
	public void editSelectBook() {
		
	}
	
	public void createNewBook() {
		creatorBooks.createNew(window);
		
//		booksList.add(new Book("J.K.Roaling"));
		ArrayList<String> arList = new ArrayList<String>();
		for(Book book : booksList) {
			arList.add(book.getTitle());
		}
		window.setMainListData(arList.toArray(new String[0]));
//		window.validate();
	}
}
