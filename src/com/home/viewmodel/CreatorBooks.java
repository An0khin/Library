package com.home.viewmodel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class CreatorBooks {
	List<Book> booksList;
	int curId = 0;
	
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

		int option = JOptionPane.showConfirmDialog(frame, message, "Login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
		    if(!usernameField.getText().isEmpty()) {
		    	book = new Book(usernameField.getText(), curId++);
		    	if(!authorField.getText().isEmpty()) {
			    	Author author = new Author(authorField.getText());
			    	book.setAuthor(author);
			    }
		    }
		}
				
		if(book != null) {
			BuilderXML.addBook(book);
			booksList.add(book);
		}
	}
	
	public void editBook(Book book) {
		for(int i = 0; i < booksList.size(); i++) {
			if(booksList.get(i).getId() == book.getId()) {
				booksList.set(i, book);
			}
		}
		BuilderXML.editBookById(book, book.getId());
	}
	
	public void addBook(Book book) {
		BuilderXML.addBook(book);
		booksList.add(book);
	}
		
	public void deleteBook(Book book) {
		//Deleting book and decrement id
		boolean deleted = false;
		for(Book tmp : booksList) {
			if(deleted) {
				tmp.setId(tmp.getId() - 1);
			}
			if(book.getId() == tmp.getId() && !deleted) {
				deleted = true;
			}
		}
		booksList.remove(book);
		BuilderXML.removeBook(book);
		curId--;
		
	}
}
