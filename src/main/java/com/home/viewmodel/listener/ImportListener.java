package com.home.viewmodel.listener;

import com.home.viewmodel.Library;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportListener implements ActionListener {

    Library library;

    public ImportListener(Library library) {
        this.library = library;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setFileFilter(new FileNameExtensionFilter("Extensible Markup Language", "xml"));
        int option = chooser.showOpenDialog(null);

        if(option == JFileChooser.APPROVE_OPTION) {
            library.importXml(chooser.getSelectedFile());
        }

    }

}
