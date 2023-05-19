package com.home.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FileListener implements ActionListener {
	File file;
	JLabel text;
	
	public FileListener(JLabel text, File file) {
		this.file = file;
		this.text = text;
		updateText();
	}
	
	private void updateText() {
		if(file != null) {
			text.setText("Selected: " + file.getName());
		}
	}
	
	public File getSelected() {
		return file;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		int option = fileChooser.showOpenDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			updateText();
		}
	}
}