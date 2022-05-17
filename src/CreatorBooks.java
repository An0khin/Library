import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class CreatorBooks {
	List<Book> booksList;
	
	public CreatorBooks(List<Book> booksList) {
		this.booksList = booksList;
	}
	
	public void createNew(JFrame frame) {
		Book book = null;
		JTextField usernameField = new JTextField();
		JTextField authorField = new JTextField();
		List<JTextComponent> listTexts = new ArrayList<>();
		listTexts.add(usernameField);
		listTexts.add(authorField);
		
		Object[] message = {
		    "Title:", usernameField,
		    "Author:", authorField
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
		    if(!usernameField.getText().isEmpty()) {
		    	book = new Book(usernameField.getText());
		    	if(!authorField.getText().isEmpty()) {
			    	Author author = new Author(authorField.getText());
			    	book.setAuthor(author);
			    }
		    }
		}
		
		BuilderXML.addBook(book);
		
		if(book != null) {
			booksList.add(book);
		}
	}
}
