package de.nrw.lichtenau.ian.db_copy.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.nrw.lichtenau.ian.db_copy.ConfUtil;
import de.nrw.lichtenau.ian.db_copy.DBProp;

@SuppressWarnings("serial")
public class DBPropDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldUrl;
	private JTextField textFieldDriver;
	private JTextField textFieldUser;
	private JTextField textFieldPass;
	private DBProp dbprop;
	private Window window;
	
	public void setWindow(Window window) {
		this.window = window;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DBPropDlg dialog = new DBPropDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDbprop(DBProp dbprop) {
		this.dbprop = dbprop;
	}

	/**
	 * Create the dialog.
	 */
	public DBPropDlg() {
		setTitle("DB Edit");
		setBounds(100, 100, 198, 172);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		contentPanel.add(lblNewLabel_1);
		
		textFieldName = new JTextField();
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("URL :");
		contentPanel.add(lblNewLabel_4);
		
		textFieldUrl = new JTextField();
		contentPanel.add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Treiber :");
		contentPanel.add(lblNewLabel_3);
		
		textFieldDriver = new JTextField();
		contentPanel.add(textFieldDriver);
		textFieldDriver.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("User :");
		contentPanel.add(lblNewLabel_2);
		
		textFieldUser = new JTextField();
		contentPanel.add(textFieldUser);
		textFieldUser.setColumns(10);
		{
			JLabel lblNewLabel = new JLabel("Passwort :");
			contentPanel.add(lblNewLabel);
		}
		
		textFieldPass = new JTextField();
		contentPanel.add(textFieldPass);
		textFieldPass.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(dbprop == null) {
							dbprop = new DBProp();
							ConfUtil.verb.add(dbprop);
							
						}

						
						dbprop.setDriver(textFieldDriver.getText());
						dbprop.setName(textFieldName.getText());
						dbprop.setPass(textFieldPass.getText());
						dbprop.setUrl(textFieldUrl.getText());
						dbprop.setUser(textFieldUser.getText());
						try {
							ConfUtil.writeconf();
						}catch(FileNotFoundException e1){
							System.out.println("Keine Zugriffsrechte : " + e1);
						}
						window.refresh();
						dispose();

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public JTextField getTextFieldName() {
		return textFieldName;
	}
	public JTextField getTextFieldUrl() {
		return textFieldUrl;
	}
	public JTextField getTextFieldDriver() {
		return textFieldDriver;
	}
	public JTextField getTextFieldUser() {
		return textFieldUser;
	}
	public JTextField getTextFieldPass() {
		return textFieldPass;
	}
}
