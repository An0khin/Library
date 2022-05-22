import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
		window.setButEditListener(listeners.getEditActionListener());
		window.setMainListSelectionListener(listeners.getListSelectionListener());
	}
	
	public void editSelectBook() {
		System.out.println(window.hasSelected());
		if(window.hasSelected()) {
			String title = window.getTitleValue();
			
			Book curBook = findBookById(getIdFromTitle(title));
			int curId = curBook.getId();
			
			JTextField titleField = new JTextField(curBook.getTitle());
			JTextField authorField = new JTextField(curBook.getAuthor().getName());
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(listeners.getDeleteActionListener());
			
			Object[] message = {
				    "Title:", titleField,
				    "Author:", authorField,
				    deleteButton
				};

			int option = JOptionPane.showConfirmDialog(window, message, curBook.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			
			if (option == JOptionPane.OK_OPTION) {
								
				curBook.setTitle(titleField.getText());
				curBook.setAuthor(new Author(authorField.getText()));
				
				creatorBooks.editBook(curBook);
			}
		}
		updateList();
	}
	
	public void createNewBook() {
		creatorBooks.createNew(window);
		updateList();
	}
	
	public void updateList() {
		ArrayList<String> arList = new ArrayList<String>();
		for(Book book : booksList) {
			arList.add("#id=" + book.getId() + "# " + book.getTitle());
		}
		window.setMainListData(arList.toArray(new String[0]));
	}
	
	public Book findBookById(int id) {
		//System.out.println(id);
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
			
			Object[] message = {
				"Title:", curBook.getTitle(),
				"Author:", curBook.getAuthor().getName()
			};
			
			JOptionPane.showMessageDialog(window, message, curBook.getTitle(), JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void deleteCurrent() {
		if(window.hasSelected()) {
			String title = window.getTitleValue();
			
			Book curBook = findBookById(getIdFromTitle(title));
			
			int option = JOptionPane.showConfirmDialog(window, "Do you wanna delete?");
			
			if(option == JOptionPane.OK_OPTION) {
				creatorBooks.deleteBook(curBook);
				updateList();
			}
		}
	}
}
