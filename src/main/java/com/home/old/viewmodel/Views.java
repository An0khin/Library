package com.home.old.viewmodel;
public enum Views {
	BOOKS(0), AUTHORS(1), GENRES(2);
	
	private int numb;
	
	Views(int numb) {
		this.numb = numb;
	}
	
	public int getNumb() {
		return numb;
	}
}
