package com.home.old.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.home.old.model.Book;
import com.home.old.model.Genres;
import com.home.old.viewmodel.BookManager;
import com.home.old.viewmodel.Library;

public class EditListener implements ActionListener{
	
	Library library;
	Book currentBook;
	
	public EditListener(Library library) {
		this.library = library;
		this.currentBook = null;
	}
	
	public void setBook(Book currentBook) {
		this.currentBook = currentBook;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentBook != null) {
			JTextField usernameField = new JTextField(currentBook.getTitle());
			JTextField authorField = new JTextField(currentBook.getAuthor());
			JComboBox<String> genresField = new JComboBox<>(Genres.getArray());
			genresField.setSelectedItem(currentBook.getGenre());
			genresField.setEditable(true);
			JTextField pagesField = new JTextField(Integer.toString(currentBook.getPages()));
			
			JTextArea descriptionField = new JTextArea(currentBook.getDescription(), 5, 5);
			JScrollPane descriptionPane = new JScrollPane(descriptionField);
			descriptionPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			descriptionPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			descriptionPane.setAutoscrolls(true);
			
			Integer[] ratingAr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			JComboBox<Integer> ratingField = new JComboBox<>(ratingAr);
			ratingField.setSelectedItem(currentBook.getRating());
			
			JLabel selectText = new JLabel();
			FileListener listener = new FileListener(selectText, currentBook.getFile());
			JButton button = new JButton("SELECT FILE: ");
			button.addActionListener(listener);
						
			Object[] message = {
			    "Title:", usernameField,
			    "Author:", authorField,
			    "Genres:", genresField,
			    "Pages:", pagesField,
			    "Rating:", ratingField,
			    "Description:", descriptionPane,
			    button, selectText
			};

			int option = JOptionPane.showConfirmDialog(null, message, "New Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
			    // Creating book by books' creator
								
				String title = usernameField.getText();
				String author = authorField.getText();
				String genre = (String) genresField.getSelectedItem();
				String pages = pagesField.getText();
				int rating = (int) ratingField.getSelectedItem();
				String description = descriptionField.getText();
				File file = listener.getSelected();
				String url = currentBook.getUrl();
									
				Book newBook = BookManager.createBookForReplace(currentBook, title, author, genre, pages, rating, description, file, url);

				library.editBook(currentBook, newBook);
				
				if(url.isEmpty())
					library.setUrl(newBook);
			}
		}
	}
}
