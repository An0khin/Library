import java.util.ArrayList;
import java.util.List;

public class Author {
	private List<Book> booksList = new ArrayList<>(); //Will be replaced by database
	private String name;
	private String description;
	
	public static Author getNull() {
		return new Author("");
	}
		
	public Author(String name) {
		super();
		this.name = name;
	}

	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Book> getBooksList() {
		return booksList;
	}
	public Book getBookByName(String title) { //Will be replaced by database
		Book needBook = null;
		
		for(Book book : booksList) {
			if(book.getTitle().equals(title)) {
				needBook = book;
				break;
			}
		}
		
		return needBook;
	}
	public void addBook(Book book) {
		booksList.add(book);
	}
}
