package de.nrw.lichtenau.ian.db_copy.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;

import de.nrw.lichtenau.ian.db_copy.ConfUtil;
import de.nrw.lichtenau.ian.db_copy.DBProp;

import javax.swing.JCheckBox;

public class Window {

	private JFrame frmDbCopy;
	final JList<DBProp> list = new JList<>();
	private JComboBox<DBProp> sourceDBcomboBox;
	private JComboBox<DBProp> TargetDBcomboBox;
	private JProgressBar currenttableprogressBar;
	private JProgressBar allprogressBar;
	private JCheckBox chckbxTruncateTables;
	
	private DBCopyTask copyTask;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmDbCopy.setVisible(true);
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
		frmDbCopy = new JFrame();
		frmDbCopy.setTitle("DB Copy");
		frmDbCopy.setBounds(100, 100, 450, 300);
		frmDbCopy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmDbCopy.getContentPane().add(panel, BorderLayout.WEST);
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
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBPropDlg propDlg = new DBPropDlg();
				propDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				propDlg.getTextFieldName();
				propDlg.getTextFieldDriver();
				propDlg.getTextFieldPass();
				propDlg.getTextFieldUrl();
				propDlg.getTextFieldUser();
				propDlg.setDbprop(null);
				propDlg.setWindow(Window.this);
				propDlg.setVisible(true);

			}
		});
		btnNew.setToolTipText("Neu ...");
		btnNew.setIcon(new ImageIcon(Window.class.getResource("/toolbarButtonGraphics/general/New16.gif")));
		toolBar.add(btnNew);

		JButton btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedValue() != null) {
					DBPropDlg propDlg = new DBPropDlg();
					propDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					DBProp auswahl = (DBProp) list.getSelectedValue();
					propDlg.getTextFieldName().setText(auswahl.getName());
					propDlg.getTextFieldDriver().setText(auswahl.getDriver());
					propDlg.getTextFieldPass().setText(auswahl.getPass());
					propDlg.getTextFieldUrl().setText(auswahl.getUrl());
					propDlg.getTextFieldUser().setText(auswahl.getUser());
					propDlg.setDbprop(auswahl);
					propDlg.setWindow(Window.this);
					propDlg.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frmDbCopy, "Bitte wählen sie erst eine Datenbankverbindung aus.");
				}
			}
		});
		btnEdit.setIcon(new ImageIcon(Window.class.getResource("/toolbarButtonGraphics/general/Edit16.gif")));
		btnEdit.setToolTipText("Ändern ...");
		toolBar.add(btnEdit);

		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// list.remove(list.getSelectedValue());
			}
		});
		btnDelete.setIcon(new ImageIcon(Window.class.getResource("/toolbarButtonGraphics/general/Delete16.gif")));
		btnDelete.setToolTipText("Löschen");
		toolBar.add(btnDelete);

		try {
			ConfUtil.readconf();
		} catch (IOException e) {
			// fixme ian : joptionpane statt printstacktrace
			e.printStackTrace();
		}

		panel.add(list, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		frmDbCopy.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 0, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);

		JLabel lblQuelle = new JLabel("Quelle");
		panel_2.add(lblQuelle);

		sourceDBcomboBox = new JComboBox<>();
		panel_2.add(sourceDBcomboBox);

		JLabel lblZiel = new JLabel("Ziel");
		panel_2.add(lblZiel);

		TargetDBcomboBox = new JComboBox<>();
		panel_2.add(TargetDBcomboBox);
		
		chckbxTruncateTables = new JCheckBox("Zieltabellen löschen");
		panel_2.add(chckbxTruncateTables);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);

		currenttableprogressBar = new JProgressBar();
		panel_3.add(currenttableprogressBar);

		allprogressBar = new JProgressBar();
		panel_3.add(allprogressBar);

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		JButton btnNewButton = new JButton("Kopieren");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				copyTask=new DBCopyTask(Window.this);
				copyTask.execute();
				System.out.println("nach new DBCopyTask.execute()");
			}
		});
		panel_4.add(btnNewButton);

		refresh();
	}

	public void refresh() {
		@SuppressWarnings("serial")
		AbstractListModel<DBProp> myListModel = new AbstractListModel<DBProp>() {
			public int getSize() {
				return ConfUtil.verb.size();
			}

			public DBProp getElementAt(int index) {
				return ConfUtil.verb.get(index);
			}
		};
		list.setModel(myListModel);
		sourceDBcomboBox.setModel(new DefaultComboBoxModel<DBProp>(ConfUtil.verb.toArray(new DBProp[ConfUtil.verb.size()])));
		TargetDBcomboBox.setModel(new DefaultComboBoxModel<DBProp>(ConfUtil.verb.toArray(new DBProp[ConfUtil.verb.size()])));

	}

	public JComboBox<DBProp> getComboBox_1() {
		return sourceDBcomboBox;
	}

	public JComboBox<DBProp> getComboBox() {
		return TargetDBcomboBox;
	}

	public JProgressBar getCurrenttableprogressBar() {
		return currenttableprogressBar;
	}
	
	public JProgressBar getAllprogressBar() {
		return allprogressBar;
	}
	
	private static final class DBCopyTask extends SwingWorker<Integer, Integer> {
		private Window invoker;
		
		DBCopyTask(Window invoker) {
			this.invoker=invoker;
		}
		
		@Override
		protected Integer doInBackground() throws Exception {
			System.out.println("in DBCopyTask.run()");
			DBProp sconn = (DBProp) invoker.sourceDBcomboBox.getSelectedItem();
			DBProp tconn = (DBProp) invoker.TargetDBcomboBox.getSelectedItem();
			try {
				Class.forName(((DBProp) tconn).getDriver());
				Class.forName(((DBProp) sconn).getDriver());

				try (
						Connection scon = DriverManager.getConnection(sconn.getUrl(), sconn.getUser(), sconn.getPass());
						Connection tcon = DriverManager.getConnection(tconn.getUrl(), tconn.getUser(), tconn.getPass());
					) {
					DatabaseMetaData smeta = scon.getMetaData();
					DatabaseMetaData tmeta = tcon.getMetaData();
					try (ResultSet sres = smeta.getTables(null, null, "%", null)) {
						while (sres.next()) {
							String tabellenname = sres.getString("table_name");
							try (ResultSet tres = tmeta.getTables(null, null, tabellenname, null)) {
								if (tres.next()) {
									if(invoker.chckbxTruncateTables.isSelected()) {
										Statement truncate=tcon.createStatement();
										truncate.executeUpdate("truncate table "+tabellenname);
									}
									invoker.currenttableprogressBar.setMaximum(getRowCount(scon, tabellenname));
									invoker.currenttableprogressBar.setValue(0);
					            	invoker.currenttableprogressBar.setString(invoker.currenttableprogressBar.getValue()+"/"+invoker.currenttableprogressBar.getMaximum());
									invoker.currenttableprogressBar.setStringPainted(true);
									System.out.println("Progressbar initialisiert ...");
									
									List<String> sColumnNames = getColumnNames(tabellenname, smeta);
									List<String> tColumnNames = getColumnNames(tabellenname, tmeta);
									List<String> stColumnNames = new ArrayList<String>();

									for (String temp : sColumnNames) {
										for (String temp2 : tColumnNames) {
											if (temp.equals(temp2)) {
												stColumnNames.add(temp);
											}
										}
									}
//									JOptionPane.showMessageDialog(frmDbCopy, stColumnNames);
									
//									select Feld1, Feld2, ..., Feld n from Tabellenname;
//									insert into Tabellenname Feld1, Feld2, ..., Feld n values value1, value2, ..., value n;
									Statement select = scon.createStatement();
									String felderliste="";
									String fragezeichenliste = "";
									for(String columnName : stColumnNames) {
										felderliste += ", ";
										felderliste += columnName;
										fragezeichenliste += ",?";
									}
									felderliste = felderliste.replaceFirst(",", "");
									fragezeichenliste = fragezeichenliste.replaceFirst(",", "");
									try (ResultSet cont = select.executeQuery("select "+felderliste+" from "+tabellenname)) {
										//1, Königstr., 18, 33165,
										//insert into tabellenname (Feld1, Feld2, ..., Feld n) values (1, 'Königstr', 18, 33165, ...)
										//insert into tabellenname (Feld1, Feld2, ..., Feld n) values (?,?,? , ...)
										
										PreparedStatement insert = tcon.prepareStatement("insert into "+tabellenname+" ("+felderliste+") values ("+fragezeichenliste+")");
										while (cont.next()) {
											for(int i = 1 ; i <= stColumnNames.size(); i++) {
												insert.setObject(i,cont.getObject(i));
											}
											
//									        SwingUtilities.invokeLater(new Runnable() {
//									            @Override
//									            public void run() {
//									            	invoker.currenttableprogressBar.setValue(invoker.currenttableprogressBar.getValue()+1);
//									            	System.out.println("in invokeLater Progressbar-Update ...");
//									            }
//									        });
//									        System.out.println("nach invokeLater Progressbar-Update ...");
							            	invoker.currenttableprogressBar.setValue(invoker.currenttableprogressBar.getValue()+1);
							            	invoker.currenttableprogressBar.setString(invoker.currenttableprogressBar.getValue()+"/"+invoker.currenttableprogressBar.getMaximum());
//									        System.out.println("nach Progressbar-Update ...");
							            	insert.execute();
										}
									}
								}else {
									System.out.println("Tabelle '"+tabellenname+"' ist in Zieldatenbank nicht vorhanden");
								}
							}
						}
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(invoker.frmDbCopy, e.getMessage());
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		
		private List<String> getColumnNames(String tabellenname, DatabaseMetaData meta) throws SQLException {
			List<String> columnNames = new ArrayList<>(); 
			try (ResultSet res2 = meta.getColumns(null, null, tabellenname, "%")) {

				while (res2.next()) {
					columnNames.add(res2.getString("column_name"));
				}
			}
			return columnNames;
		}
		
		private int getRowCount(Connection scon, String tabellenname) throws SQLException {
			Statement select = scon.createStatement();
			ResultSet rowCount = select.executeQuery("select count('x') from "+tabellenname);
			rowCount.next();
			return rowCount.getInt(1);
			
		}

	}
	public JCheckBox getChckbxTruncateTables() {
		return chckbxTruncateTables;
	}
}