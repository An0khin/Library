package com.home.old.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.home.old.viewmodel.Library;

public class ExportListener implements ActionListener {

	Library library;

	public ExportListener(Library library) {
		this.library = library;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileFilter(new FileNameExtensionFilter("Extensible Markup Language", "xml"));
		int option = chooser.showSaveDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			library.export(chooser.getSelectedFile());
		}
	}

}
