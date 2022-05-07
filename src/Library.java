import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<Author> authorsList = new ArrayList<>(); //Will be replaced by database
	private List<Book> booksList = new ArrayList<>(); //Will be replaced by database
	
	public static void main(String[] args) {
		Library libr = new Library();
		libr.start();		
	}
	
	public void start() {
		LibraryWindow window = LibraryWindow.getWindow(); //Will be library's gui
	}
}
