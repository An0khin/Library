package com.home.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import com.home.model.Genres;
import com.home.model.Book;
import com.home.viewmodel.BookManager;
import com.home.viewmodel.Library;
/*
 * Created by JFormDesigner on Fri May 06 19:36:43 YEKT 2022
 */



/**
 * @author get
 */
public class LibraryWindow extends JFrame implements Observer{		
	public Library library;
	
	public LibraryWindow(Library library) {
		library.addObserver(this);
		this.library = library;
		initComponents();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//Library libr = (Library) o;
		mainList.setListData(library.getList().toArray(new String[0]));
	}
	
	public void viewBook(Book book) {
		bookReviewPanel.removeAll();
		JTextField idField = new JTextField("Id: " + book.getId());
		JTextField usernameField = new JTextField("Title: " + book.getTitle());
		JTextField authorField = new JTextField("Author: " + book.getAuthor());
		JTextField genresField = new JTextField("Genre: " + book.getGenre().getName());
		JTextField pagesField = new JTextField("Pages: " + book.getPages());
		JTextArea descriptionField = new JTextArea("Description: " + book.getDescription());
		JTextField ratingField = new JTextField("Rating: " + book.getRating());
					
		Object[] message = {
				idField,
				usernameField,
				authorField,
				genresField,
				pagesField,
				descriptionField,
				ratingField
		};
		
		for(Object obj : message) {
			JTextComponent comp = (JTextComponent) obj;
			//comp.setEnabled(false);
			comp.setEditable(false);
			bookReviewPanel.add(comp);
		}
		this.validate();
	}
	
	private class NewListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// Show window with input areas
			JTextField usernameField = new JTextField();
			JTextField authorField = new JTextField();
			JComboBox<Genres> genresField = new JComboBox<>(Genres.values());
			JTextField pagesField = new JTextField();
			JTextArea descriptionField = new JTextArea();
			
			Integer[] rating = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			JComboBox<Integer> ratingField = new JComboBox(rating);
						
			Object[] message = {
			    "Title:", usernameField,
			    "Author:", authorField,
			    "Genres:", genresField,
			    "Pages:", pagesField,
			    "Description:", descriptionField,
			    "Rating:", ratingField
			};

			int option = JOptionPane.showConfirmDialog(null, message, "New Book", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
			    // Creating book by books' creator
				Book book = BookManager.createBook(usernameField.getText(), authorField.getText(),(Genres) genresField.getSelectedItem(), 
						Integer.parseInt(pagesField.getText()), descriptionField.getText(), (int) ratingField.getSelectedItem());
				library.addBook(book);
			}
			// Move arguments from window to books' creator and later move it to library.newBook()
		}
	}
	
	private class EditListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class CheckerClicks extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JList list = (JList) e.getSource();
			if(e.getClickCount() == 2 && !list.isSelectionEmpty()) {
				String record = (String) list.getSelectedValue();
				//library.printBook(BookManager.getIdFromRecord(record));
				viewBook(library.getBookById(BookManager.getIdFromRecord(record)));
			}
			// TODO Auto-generated method stub
			super.mouseClicked(e);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		viewMenu = new JMenu();
		booksMenu = new JMenuItem();
		authorsMenu = new JMenuItem();
		genresMenu = new JMenuItem();
		scrollPane1 = new JScrollPane();
		mainList = new JList();
		splitPane1 = new JSplitPane();
		buttonNew = new JButton();
		buttonEdit = new JButton();
		bookReviewPanel = new JPanel();

		//======== this ========
		setTitle("Own Library");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== viewMenu ========
			{
				viewMenu.setText("View");

				//---- booksMenu ----
				booksMenu.setText("Books");
				viewMenu.add(booksMenu);

				//---- authorsMenu ----
				authorsMenu.setText("Authors");
				viewMenu.add(authorsMenu);

				//---- genresMenu ----
				genresMenu.setText("Genres");
				viewMenu.add(genresMenu);
			}
			menuBar1.add(viewMenu);
		}
		setJMenuBar(menuBar1);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(mainList);
		}
		contentPane.add(scrollPane1, BorderLayout.CENTER);

		//======== splitPane1 ========
		{
			splitPane1.setResizeWeight(0.5);

			//---- buttonNew ----
			buttonNew.setText("NEW");
			splitPane1.setLeftComponent(buttonNew);

			//---- buttonEdit ----
			buttonEdit.setText("EDIT");
			splitPane1.setRightComponent(buttonEdit);
		}
		contentPane.add(splitPane1, BorderLayout.SOUTH);

		//======== bookReviewPanel ========
		{
			bookReviewPanel.setPreferredSize(new Dimension(200, 0));
			bookReviewPanel.setLayout(new BoxLayout(bookReviewPanel, BoxLayout.Y_AXIS));
		}
		contentPane.add(bookReviewPanel, BorderLayout.EAST);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		mainList.addMouseListener(new CheckerClicks());
		buttonNew.addActionListener(new NewListener());
		buttonEdit.addActionListener(new EditListener());
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu viewMenu;
	private JMenuItem booksMenu;
	private JMenuItem authorsMenu;
	private JMenuItem genresMenu;
	private JScrollPane scrollPane1;
	private JList mainList;
	private JSplitPane splitPane1;
	private JButton buttonNew;
	private JButton buttonEdit;
	private JPanel bookReviewPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
