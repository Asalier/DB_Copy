package de.nrw.lichtenau.ian.db_copy.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.nrw.lichtenau.ian.db_copy.ConfUtil;
import de.nrw.lichtenau.ian.db_copy.DBProp;
import javax.swing.border.BevelBorder;

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
		
		JLabel lblNewLabel = new JLabel("Datenbankverbindungen");
		panel.add(lblNewLabel, BorderLayout.NORTH);
		JButton btnNeu = new JButton("Neu");
		panel.add(btnNeu, BorderLayout.SOUTH);
		
		JPanel dbListPanel = new JPanel();
		panel.add(dbListPanel, BorderLayout.CENTER);
		GridBagLayout gbl_dbListPanel = new GridBagLayout();
		gbl_dbListPanel.columnWidths = new int[]{70, 0};
		gbl_dbListPanel.rowHeights = new int[]{101, 15, 0};
		gbl_dbListPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_dbListPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		dbListPanel.setLayout(gbl_dbListPanel);
		try {
			List<DBProp> dbconfs=ConfUtil.getdbconf();
			GridBagConstraints c=new GridBagConstraints();
			for(DBProp dbconf:dbconfs) {
				c.gridwidth=1;
				JLabel l=new JLabel(dbconf.getName());
				gbl_dbListPanel.setConstraints(l, c);
				dbListPanel.add(l);
				JButton edit=new JButton(new ImageIcon("icons/jlfgr-1_0/toolbarButtonGraphics/general/Edit16.gif"));
				gbl_dbListPanel.setConstraints(edit, c);
				dbListPanel.add(edit);
				JButton delete=new JButton(new ImageIcon("icons/jlfgr-1_0/toolbarButtonGraphics/general/Delete16.gif"));
				c.gridwidth=GridBagConstraints.REMAINDER;
				gbl_dbListPanel.setConstraints(delete, c);
				dbListPanel.add(delete);
			}
		} catch (IOException e1) {
			// fixme ian : joptionpane statt printstacktrace 
			e1.printStackTrace();
		}
		
	}

}
