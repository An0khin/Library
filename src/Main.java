import com.home.model.BookList;
import com.home.view.LibraryWindow;
import com.home.viewmodel.Library;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();
		LibraryWindow window = new LibraryWindow(library);
		
		BookList books = new BookList();
//		books.addBook(new com.home.model.Book(0, "Hello", "auth", com.home.model.Genres.NULL, 0, "", 5));
//		books.addBook(new com.home.model.Book(1, "er", "ty", com.home.model.Genres.NULL, 0, "", 5));
		
		library.setModel(books);
	}
}