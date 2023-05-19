package com.home.old.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.home.old.viewmodel.Library;
import com.home.old.viewmodel.Views;

public class AuthorsMenuListener implements ActionListener {

	Library library;

	public AuthorsMenuListener(Library library) {
		this.library = library;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		library.setView(Views.AUTHORS);
	}

}
