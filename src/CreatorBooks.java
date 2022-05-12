import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

import windows.NewWindow;

public class CreatorBooks {
	List<Book> booksList;
	
	public CreatorBooks(List<Book> booksList) {
		this.booksList = booksList;
	}
	
	public void createNew(JFrame frame) {
		JDialog dialogCreate = new NewWindow(frame);
		dialogCreate.setVisible(true);
	}
}
