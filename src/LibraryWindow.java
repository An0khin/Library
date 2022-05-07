import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri May 06 19:36:43 YEKT 2022
 */



/**
 * @author get
 */
public class LibraryWindow extends JFrame {
	private static LibraryWindow window;
	
	public static LibraryWindow getWindow() {
		if(window == null) {
			window = new LibraryWindow();
		}
		return window;
	}
	
	private LibraryWindow() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		viewMenu = new JMenu();
		booksMenu = new JMenuItem();
		authorsMenu = new JMenuItem();
		scrollPane1 = new JScrollPane();
		mainList = new JList();
		scrollPane2 = new JScrollPane();
		infoArea = new JTextArea();
		splitPane1 = new JSplitPane();
		buttonNew = new JButton();
		buttonEdit = new JButton();

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
			}
			menuBar1.add(viewMenu);
		}
		setJMenuBar(menuBar1);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(mainList);
		}
		contentPane.add(scrollPane1, BorderLayout.CENTER);

		//======== scrollPane2 ========
		{

			//---- infoArea ----
			infoArea.setEditable(false);
			scrollPane2.setViewportView(infoArea);
		}
		contentPane.add(scrollPane2, BorderLayout.EAST);

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
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu viewMenu;
	private JMenuItem booksMenu;
	private JMenuItem authorsMenu;
	private JScrollPane scrollPane1;
	private JList mainList;
	private JScrollPane scrollPane2;
	private JTextArea infoArea;
	private JSplitPane splitPane1;
	private JButton buttonNew;
	private JButton buttonEdit;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
