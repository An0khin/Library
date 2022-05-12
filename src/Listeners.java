import java.awt.event.ActionListener;

public class Listeners {
	private Library library;
	
	Listeners(Library library) {
		this.library = library;
	}
	
	public ActionListener getNewActionListener() {
		return e -> library.createNewBook();
	}
	
	public ActionListener getEditActionListener() {
		return e -> library.editSelectBook();
	}
}
