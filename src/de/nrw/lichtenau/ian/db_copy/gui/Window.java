package de.nrw.lichtenau.ian.db_copy.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import de.nrw.lichtenau.ian.db_copy.ConfUtil;
import de.nrw.lichtenau.ian.db_copy.DBProp;

public class Window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Datenbankverbindungen");
		northPanel.add(lblNewLabel, BorderLayout.NORTH);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		northPanel.add(toolBar, BorderLayout.SOUTH);
		
		JButton btnNew = new JButton("");
		btnNew.setToolTipText("Neu ...");
		btnNew.setIcon(new ImageIcon(Window.class.getResource("/toolbarButtonGraphics/general/New16.gif")));
		toolBar.add(btnNew);
		
		final JList list = new JList();
		
		JButton btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedValue() != null) {
					DBPropDlg propDlg=new DBPropDlg();
					propDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					DBProp auswahl = (DBProp) list.getSelectedValue();
					propDlg.getTextFieldName().setText(auswahl.getName());
					propDlg.getTextFieldDriver().setText(auswahl.getDriver());
					propDlg.getTextFieldPass().setText(auswahl.getPass());
					propDlg.getTextFieldUrl().setText(auswahl.getUrl());
					propDlg.getTextFieldUser().setText(auswahl.getUser());
					propDlg.setVisible(true);	
				}else{
					JOptionPane.showMessageDialog(frame, "Bitte wählen sie erst eine Datenbankverbindung aus.");
				}
			}
		});
		btnEdit.setIcon(new ImageIcon(Window.class.getResource("/toolbarButtonGraphics/general/Edit16.gif")));
		btnEdit.setToolTipText("Ändern ...");
		toolBar.add(btnEdit);
		
		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(Window.class.getResource("/toolbarButtonGraphics/general/Delete16.gif")));
		btnDelete.setToolTipText("Löschen");
		toolBar.add(btnDelete);
		
		final List<DBProp> values=new ArrayList<DBProp>();
		try {
			values.addAll(ConfUtil.getdbconf());
		} catch (IOException e) {
//			 fixme ian : joptionpane statt printstacktrace 
			e.printStackTrace();
		}
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		panel.add(list, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblQuelle = new JLabel("Quelle");
		panel_2.add(lblQuelle);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(values.toArray()));
		panel_2.add(comboBox_1);
		
		JLabel lblZiel = new JLabel("Ziel");
		panel_2.add(lblZiel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(values.toArray()));
		panel_2.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JProgressBar progressBar = new JProgressBar();
		panel_3.add(progressBar);
		
		JProgressBar progressBar_1 = new JProgressBar();
		panel_3.add(progressBar_1);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		JButton btnNewButton = new JButton("New button");
		panel_4.add(btnNewButton);
		

	}
}
