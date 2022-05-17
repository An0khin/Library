import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;

public class Listeners {
	private Library library;
	
	Listeners(Library library) {
		this.library = library;
	}
	
	public ActionListener getNewActionListener() {
		return e -> library.createNewBook();
	}
	
	public ActionListener getEditActionListener() {
		return e -> library.editSelectBook();
	}
	
	public ListSelectionListener getListSelectionListener() {
		return e -> {
			if(!e.getValueIsAdjusting()) {
				library.openBook();
			}
		};
	}
}
