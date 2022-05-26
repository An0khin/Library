package com.home.view;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun May 22 21:07:54 YEKT 2022
 */



/**
 * @author get
 */
public class AutoRecordsList extends JPopupMenu {
	public AutoRecordsList() {
		initComponents();
	}
	
	public void setRecordsList(String[] records) {
		recordsList.setListData(records);
		recordsList.updateUI();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		recordsList = new JList<>();

		//======== this ========

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(recordsList);
		}
		add(scrollPane1);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JList<String> recordsList;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
