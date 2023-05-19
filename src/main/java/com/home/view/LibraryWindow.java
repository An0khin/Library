package com.home.view;

import javax.swing.*;
import java.awt.*;

public class LibraryWindow extends JFrame {
    public LibraryWindow() {
        setSettings();
        initComponents();
    }

    private void initComponents() {
        Container contentPane = setupContentPane();

        JMenuBar menuBar = setupMenuBar();
        JPanel buttonsPanel = setupButtonsPanel();
        JPanel mainPanel = setupMainPanel();

        setJMenuBar(menuBar);
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private void setSettings() {
        setTitle("Own Library");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(460, 410);
        setLocationRelativeTo(getOwner());
    }
    private Container setupContentPane() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        return contentPane;
    }
    private JMenuBar setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = setupFileMenu();
        JMenu viewMenu = setupViewMenu();

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }
    private JMenu setupFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem importXMLMenu = new JMenuItem("Import XML");
        JMenuItem exportXMLMenu = new JMenuItem("Export XML");

        fileMenu.add(importXMLMenu);
        fileMenu.add(exportXMLMenu);

        return fileMenu;
    }
    private JMenu setupViewMenu() {
        JMenu viewMenu = new JMenu("View");

        JMenuItem booksMenu = new JMenuItem("Books");
        JMenuItem authorsMenu = new JMenuItem("Authors");
        JMenuItem genresMenu = new JMenuItem("Genres");

        viewMenu.add(booksMenu);
        viewMenu.add(authorsMenu);
        viewMenu.add(genresMenu);

        return viewMenu;
    }
    private JPanel setupButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        JButton buttonNew = setupButtonNew();
        JButton buttonEdit = setupButtonEdit();
        JButton buttonDelete = setupButtonDelete();

        buttonsPanel.add(buttonNew);
        buttonsPanel.add(buttonEdit);
        buttonsPanel.add(buttonDelete);

        return buttonsPanel;
    }
    private JButton setupButtonNew() {
        JButton buttonNew = new JButton("NEW");
        buttonNew.setMinimumSize(new Dimension(39, 25));
        buttonNew.setPreferredSize(new Dimension(153, 25));
        buttonNew.setIconTextGap(6);
        buttonNew.setMaximumSize(new Dimension(1920, 100));

        return buttonNew;
    }
    private JButton setupButtonEdit() {
        JButton buttonEdit = new JButton("EDIT");
        buttonEdit.setMaximumSize(new Dimension(1920, 100));
        buttonEdit.setPreferredSize(new Dimension(153, 25));

        return buttonEdit;
    }
    private JButton setupButtonDelete() {
        JButton buttonDelete = new JButton("DELETE");
        buttonDelete.setMaximumSize(new Dimension(1920, 100));
        buttonDelete.setPreferredSize(new Dimension(153, 25));

        return buttonDelete;
    }
    private JPanel setupMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JScrollPane mainScrollPane = setupMainScrollPane();
        JPanel bookReviewPanel = setupBookReviewPanel();

        mainPanel.add(mainScrollPane);
        mainPanel.add(bookReviewPanel);

        return mainPanel;
    }
    private JScrollPane setupMainScrollPane() {
        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setMinimumSize(new Dimension(200, 23));
        scrollPane.setPreferredSize(new Dimension(200, 146));

        JList<String> mainList = setupMainList();
        scrollPane.setViewportView(mainList);

        return scrollPane;
    }
    private JList<String> setupMainList() {
        JList<String> mainList = new JList<>();

        mainList.setMinimumSize(new Dimension(200, 54));
        mainList.setPreferredSize(new Dimension(200, 54));
        mainList.setMaximumSize(new Dimension(20000, 54));

        return mainList;
    }
    private JPanel setupBookReviewPanel() {
        JPanel bookReviewPanel = new JPanel();

        bookReviewPanel.setLayout(new BoxLayout(bookReviewPanel, BoxLayout.Y_AXIS));

        return bookReviewPanel;
    }
}
