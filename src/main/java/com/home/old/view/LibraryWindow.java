package com.home.old.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.JTextComponent;

import com.home.old.model.Book;
import com.home.old.view.listeners.AuthorsMenuListener;
import com.home.old.view.listeners.BooksMenuListener;
import com.home.old.view.listeners.BrowseUrlListener;
import com.home.old.view.listeners.DeleteListener;
import com.home.old.view.listeners.EditListener;
import com.home.old.view.listeners.ExportListener;
import com.home.old.view.listeners.GenresMenuListener;
import com.home.old.view.listeners.ImportListener;
import com.home.old.view.listeners.NewListener;
import com.home.old.view.listeners.ViewListener;
import com.home.old.viewmodel.BookManager;
import com.home.old.viewmodel.Library;
/*
 * Created by JFormDesigner on Fri May 06 19:36:43 YEKT 2022
 */



/**
 * @author get
 */
public class LibraryWindow extends JFrame implements Observer{		
	private Library library;
	private Book currentBook;
	private NewListener newListener;
	private EditListener editListener;
	private DeleteListener deleteListener;
	private ImportListener importListener;
	private ExportListener exportListener;
	private BrowseUrlListener browseUrlListener;
	private ViewListener viewListener;
	private BooksMenuListener booksMenuListener;
	private AuthorsMenuListener authorsMenuListener;
	private GenresMenuListener genresMenuListener;
	
	public LibraryWindow(Library library) {
		library.addObserver(this);
		this.library = library;
		newListener = new NewListener(library);
		editListener = new EditListener(library);
		deleteListener = new DeleteListener(library);
		importListener = new ImportListener(library);
		exportListener = new ExportListener(library);
		browseUrlListener = new BrowseUrlListener(library);
		viewListener = new ViewListener(library);
		booksMenuListener = new BooksMenuListener(library);
		authorsMenuListener = new AuthorsMenuListener(library);
		genresMenuListener = new GenresMenuListener(library);
		initComponents();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//Library libr = (Library) o;
		mainList.setListData(library.getList().toArray(new String[0]));
		currentBook = null;
		
		updateListeners();
		
		bookReviewPanel.removeAll();
		bookReviewPanel.updateUI();
	}
	
	private void updateListeners() {
		editListener.setBook(currentBook);
		deleteListener.setBook(currentBook);
		browseUrlListener.setBook(currentBook);
		viewListener.setBook(currentBook);
	}
	
	public void viewBook(Book book) {
		bookReviewPanel.removeAll();
		bookReviewPanel.updateUI();
		JTextField idField = new JTextField("Id: " + book.getId());
		JTextField usernameField = new JTextField("Title: " + book.getTitle());
		JTextField authorField = new JTextField("Author: " + book.getAuthor());
		JTextField genresField = new JTextField("Genre: " + book.getGenre());
		JTextField pagesField = new JTextField("Pages: " + book.getPages());
		
		JTextArea descriptionField = new JTextArea("Description: " + book.getDescription(), 5, 5);
		descriptionField.setEditable(false);
		JScrollPane description = new JScrollPane(descriptionField);
		description.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		description.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		description.setAutoscrolls(true);
		
		JTextField ratingField = new JTextField("Rating: " + book.getRating());
		
		JButton viewButton = new JButton("VIEW");
		viewButton.addActionListener(viewListener);
		
		JTextField nameFile = new JTextField("File: " + BookManager.getAddress(book.getFile()));
		nameFile.setEditable(false);
		
		JButton browseUrlButton = new JButton("BROWSE URL");
		browseUrlButton.addActionListener(browseUrlListener);
		
		JTextField nameUrl = new JTextField("URL: " + book.getUrl());
		nameUrl.setEditable(false);
					
		JTextComponent[] message = {
				idField,
				usernameField,
				authorField,
				genresField,
				pagesField,
				ratingField,
		};
		
		for(JTextComponent comp : message) {
			//comp.setEnabled(false);
			comp.setEditable(false);
			bookReviewPanel.add(comp);
		}
		
		bookReviewPanel.add(description);
		
		if(nameFile.getText().length() > 6) {
			bookReviewPanel.add(nameFile);
			bookReviewPanel.add(viewButton);
		}
		
		if(nameUrl.getText().length() > 5) {
			bookReviewPanel.add(nameUrl);
			bookReviewPanel.add(browseUrlButton);
		}
			
		this.validate();
	}

	private class CheckerClicks extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 && !mainList.isSelectionEmpty()) {
				String record = mainList.getSelectedValue();
				//library.printBook(BookManager.getIdFromRecord(record));
				currentBook = library.getBookById(BookManager.getIdFromRecord(record));
				
				updateListeners();
				
				viewBook(currentBook);
			}
			super.mouseClicked(e);
		}
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		fileMenu = new JMenu();
		importXMLMenu = new JMenuItem();
		exportXMLMenu = new JMenuItem();
		viewMenu = new JMenu();
		booksMenu = new JMenuItem();
		authorsMenu = new JMenuItem();
		genresMenu = new JMenuItem();
		buttonsPanel = new JPanel();
		buttonNew = new JButton();
		buttonEdit = new JButton();
		buttonDelete = new JButton();
		mainPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		mainList = new JList<>();
		bookReviewPanel = new JPanel();

		//======== this ========
		setTitle("Own Library");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== menuBar1 ========
		{

			//======== fileMenu ========
			{
				fileMenu.setText("File");

				//---- importXMLMenu ----
				importXMLMenu.setText("Import XML");
				fileMenu.add(importXMLMenu);

				//---- exportXMLMenu ----
				exportXMLMenu.setText("Export XML");
				fileMenu.add(exportXMLMenu);
			}
			menuBar1.add(fileMenu);

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

		//======== buttonsPanel ========
		{
			buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

			//---- buttonNew ----
			buttonNew.setText("NEW");
			buttonNew.setMinimumSize(new Dimension(39, 25));
			buttonNew.setPreferredSize(new Dimension(153, 25));
			buttonNew.setIconTextGap(6);
			buttonNew.setMaximumSize(new Dimension(1920, 100));
			buttonsPanel.add(buttonNew);

			//---- buttonEdit ----
			buttonEdit.setText("EDIT");
			buttonEdit.setMaximumSize(new Dimension(1920, 100));
			buttonEdit.setPreferredSize(new Dimension(153, 25));
			buttonsPanel.add(buttonEdit);

			//---- buttonDelete ----
			buttonDelete.setText("DELETE");
			buttonDelete.setMaximumSize(new Dimension(1920, 100));
			buttonDelete.setPreferredSize(new Dimension(153, 25));
			buttonsPanel.add(buttonDelete);
		}
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);

		//======== mainPanel ========
		{
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

			//======== scrollPane1 ========
			{
				scrollPane1.setMinimumSize(new Dimension(200, 23));
				scrollPane1.setPreferredSize(new Dimension(200, 146));

				//---- mainList ----
				mainList.setMinimumSize(new Dimension(200, 54));
				mainList.setPreferredSize(new Dimension(200, 54));
				mainList.setMaximumSize(new Dimension(20000, 54));
				scrollPane1.setViewportView(mainList);
			}
			mainPanel.add(scrollPane1);

			//======== bookReviewPanel ========
			{
				bookReviewPanel.setLayout(new BoxLayout(bookReviewPanel, BoxLayout.Y_AXIS));
			}
			mainPanel.add(bookReviewPanel);
		}
		contentPane.add(mainPanel, BorderLayout.CENTER);
		setSize(460, 410);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		mainList.addMouseListener(new CheckerClicks());
		buttonNew.addActionListener(newListener);
		buttonEdit.addActionListener(editListener);
		buttonDelete.addActionListener(deleteListener);
		importXMLMenu.addActionListener(importListener);
		exportXMLMenu.addActionListener(exportListener);
		booksMenu.addActionListener(booksMenuListener);
		authorsMenu.addActionListener(authorsMenuListener);
		genresMenu.addActionListener(genresMenuListener);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu fileMenu;
	private JMenuItem importXMLMenu;
	private JMenuItem exportXMLMenu;
	private JMenu viewMenu;
	private JMenuItem booksMenu;
	private JMenuItem authorsMenu;
	private JMenuItem genresMenu;
	private JPanel buttonsPanel;
	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JPanel mainPanel;
	private JScrollPane scrollPane1;
	private JList<String> mainList;
	private JPanel bookReviewPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
