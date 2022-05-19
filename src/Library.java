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
//	private BookWindow bookWindow;
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
		BuilderXML.buildCatalogue();
		
		this.listeners = new Listeners(this);
		window.setButNewListener(listeners.getNewActionListener());
		window.setMainListSelectionListener(listeners.getListSelectionListener());
	}
	
	public void editSelectBook() {
		
	}
	
	public void createNewBook() {
		creatorBooks.createNew(window);
		
//		booksList.add(new Book("J.K.Roaling"));
		ArrayList<String> arList = new ArrayList<String>();
		for(Book book : booksList) {
			arList.add("#id=" + book.getId() + "# " + book.getTitle());
		}
		window.setMainListData(arList.toArray(new String[0]));
//		window.validate();
	}
	
	public Book findBookById(int id) {
		System.out.println(id);
		for(Book book : booksList) {
			if(book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	
	public int getIdFromTitle(String title) {
		int endIndex = title.indexOf("#id=", 0) + 4;
		String id = title.substring(endIndex, title.indexOf("#", endIndex));
		return Integer.parseInt(id);
	}
	
	public void openBook() {
		if(window.hasSelected()) {
			String title = window.getTitleValue();
			
			Book curBook = findBookById(getIdFromTitle(title));
			
		}
	}
}
