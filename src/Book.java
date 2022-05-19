
public class Book {
	private String title;
	private Author author;
	private Genres genre;
	private int pages;
	private String description;
	private int rating;
	private int id;
	
	public Book(String title, int id) {
		super();
		this.title = title;
		this.author = Author.getNull();
		this.genre = Genres.NULL;
		this.pages = 0;
		this.description = "";
		this.rating = 0;
		this.id = id;
	}
	
	//Getters and Setters	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Genres getGenre() {
		return genre;
	}
	public void setGenre(Genres genre) {
		this.genre = genre;
	}

	public int getId() {
		return id;
	}
		
}
