package com.home.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.home.viewmodel.Library;
import com.home.viewmodel.Views;

public class GenresMenuListener implements ActionListener {

	Library library;

	public GenresMenuListener(Library library) {
		this.library = library;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		library.setView(Views.GENRES);
	}

}
