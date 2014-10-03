package de.nrw.lichtenau.ian.db_copy.fx;

/**
 * Sample Skeleton for 'Window.fxml' Controller Class
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.nrw.lichtenau.ian.db_copy.ConfUtil;
import de.nrw.lichtenau.ian.db_copy.DBProp;

public class WindowController {
	@FXML
	private Parent root;
	
	public Stage getStage() {
		return (Stage) root.getScene().getWindow();
	}
	
    @FXML // fx:id="sourceComboBox"
    private ComboBox<DBProp> sourceComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="targetComboBox"
    private ComboBox<DBProp> targetComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="truncateTargetTableCheckBox"
    private CheckBox truncateTargetTableCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="currentTableProgressBar"
    private ProgressBar currentTableProgressBar; // Value injected by FXMLLoader

    @FXML // fx:id="allTableProgressBar"
    private ProgressBar allTableProgressBar; // Value injected by FXMLLoader

    @FXML // fx:id="connectionList"
    private ListView<DBProp> connectionList; // Value injected by FXMLLoader
    
    @FXML
    private Button about;
    
	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	
	@FXML
	void onActionNew(ActionEvent event) {
		try {
			Stage stage = getStage();
			DBPropDlgController controller=FXUtil.createWindow(stage, "DB Properties", Modality.APPLICATION_MODAL, DBPropDlgController.class);
//			BUTTON clicked=FXOptionPane.showConfirmErrorYesNoCancelDlg(getStage(), "Truncate Table", "Delete target table content first?");
//			if(clicked!=null) {
//				switch (clicked) {
//				case CANCEL:
//					FXOptionPane.showMessageDlg(getStage(), "CANCEL", "Cancel clicked");
//					break;
//				case NO:
//					FXOptionPane.showMessageDlg(getStage(), "NO", "No clicked");
//					break;
//				case OK:
//					FXOptionPane.showMessageDlg(getStage(), "OK", "Ok clicked");
//					break;
//				case YES:
//					FXOptionPane.showMessageDlg(getStage(), "YES", "Yes clicked");
//					break;
//				}
//			}

			controller.setAuswahl(null, false);

			controller.getStage().show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onActionEdit(ActionEvent event) {
		if(connectionList.getSelectionModel().getSelectedItem() != null) {
			
		
			try {
				Stage stage = (Stage) root.getScene().getWindow();
				
				DBPropDlgController controller=FXUtil.createWindow(stage, "DB Properties", Modality.APPLICATION_MODAL, DBPropDlgController.class);
				DBProp auswahl = connectionList.getSelectionModel().getSelectedItem();
				
				controller.setAuswahl(auswahl, false);

				controller.getStage().show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			FXOptionPane.showMessageDlg(getStage(), "No selection", "Nothing is selected. Please select a connection first.");
			System.out.println("Select a connection first.");
		}
	}

	@FXML
	void onActionDelete(ActionEvent event) {
		if(connectionList.getSelectionModel().getSelectedItem() != null) {
			DBProp auswahl = connectionList.getSelectionModel().getSelectedItem();
			ConfUtil.conn.remove(auswahl);
			try {
				ConfUtil.writeconf();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			FXOptionPane.showMessageDlg(getStage(), "No selection", "Nothing is selected. Please select a connection first.");
			System.out.println("Select a connection first.");
		}
//		FIXME ian liste neu aufbauen
	}

	@FXML
	void onActionCopy(ActionEvent event) {
		if(connectionList.getSelectionModel().getSelectedItem() != null) {
			try {
				Stage stage = (Stage) root.getScene().getWindow();
				DBPropDlgController controller=FXUtil.createWindow(stage, "DB Properties", Modality.APPLICATION_MODAL, DBPropDlgController.class);
				DBProp auswahl = connectionList.getSelectionModel().getSelectedItem();
				
				controller.setAuswahl(auswahl, true);
				
				controller.getStage().show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
				FXOptionPane.showMessageDlg(getStage(), "No selection", "Nothing is selected. Please select a connection first.");
				System.out.println("Select a connection first.");
			}
			//		FIXME ian copie verpassen
	}
	
    @FXML
    void onActionCopyDB(ActionEvent event) {

    	DBProp sconn = sourceComboBox.getSelectionModel().getSelectedItem();
    	DBProp tconn = targetComboBox.getSelectionModel().getSelectedItem();
		Map<String, List<String>> ttablestructures= new HashMap<>();
		Map<String, List<String>> stablestructures= new HashMap<>();
		Map<String, List<String>> ctablestructures = new HashMap<>();
		String stablename = "";
		int cmax = 0;
		int amax = 0;
		try {
    			Class.forName(((DBProp) tconn).getDriver());
    			Class.forName(((DBProp) sconn).getDriver());
    			
				try (
						Connection scon = DriverManager.getConnection(sconn.getUrl(), sconn.getUser(), sconn.getPass());
						Connection tcon = DriverManager.getConnection(tconn.getUrl(), tconn.getUser(), tconn.getPass());
					){
					DatabaseMetaData smeta = scon.getMetaData();
					DatabaseMetaData tmeta = tcon.getMetaData();
						try(ResultSet sres = smeta.getTables(null, null, "%", null)
							; ResultSet tres = tmeta.getTables(null, null, "%", null)){
							currentTableProgressBar.setProgress(0);
							allTableProgressBar.setProgress(0);
							while(sres.next()) {
								stablename = sres.getString("table_name");
								stablestructures.put(stablename, new ArrayList<String>());
								try(ResultSet sres2 = smeta.getColumns(null, null, stablename, "%")){
									while(sres2.next()) {
										stablestructures.get(stablename).add(sres2.getString("column_name"));
									}
								}
							}
							cmax = getRowCount(scon, stablename);
						}
						try(ResultSet tres = tmeta.getTables(null, null, "%", null)){
							while(tres.next()) {
								String ttablename = tres.getString("table_name");
								ttablestructures.put(ttablename, new ArrayList<String>());
								ctablestructures.put(ttablename, new ArrayList<String>());
								try(ResultSet tres2 = tmeta.getColumns(null, null, ttablename, "%")){
									while(tres2.next()) {
										ttablestructures.get(ttablename).add(tres2.getString("column_name"));
										ctablestructures.get(ttablename).add(tres2.getString("column_name"));
									}
								}
							}
						}
						ctablestructures.keySet().retainAll(stablestructures.keySet());
						amax = ctablestructures.keySet().size();
						for(String ctablename : ctablestructures.keySet()) {
							ctablestructures.get(ctablename).retainAll(stablestructures.get(ctablename));
						}
						
						for(String ctablename : ctablestructures.keySet()) {
							Statement select = scon.createStatement();
							String columnlist="";
							String fragezeichenliste = "";
							for(String columnName : ctablestructures.get(ctablename)) {
								columnlist += ", ";
								columnlist += columnName;
								fragezeichenliste += ",?";
							}
							columnlist = columnlist.replaceFirst(",", "");
							fragezeichenliste = fragezeichenliste.replaceFirst(",", "");
							try (ResultSet cont = select.executeQuery("select "+columnlist+" from "+ctablename)) {
								
								PreparedStatement insert = tcon.prepareStatement("insert into "+ctablename+" ("+columnlist+") values ("+fragezeichenliste+")");
								while (cont.next()) {
									for(int i = 1 ; i <= ctablestructures.get(ctablename).size(); i++) {
										insert.setObject(i,cont.getObject(i));
									}
								insert.execute();
								currentTableProgressBar.setProgress((currentTableProgressBar.getProgress()+ 1) / cmax );
							}
								allTableProgressBar.setProgress(allTableProgressBar.getProgress()+ 1 / amax);
						}
						}
//FIXME mars: später wieder rein, wenn klar ist, dass die betreffende Tabelle tatsächlich im nächsten Schritt kopiert wird.
//						if(truncateTargetTableCheckBox.isSelected()) {
//							Statement truncate=tcon.createStatement();
//							truncate.executeUpdate("truncate table "+stablename);
//						}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }


    @FXML
    void onActionAbout(ActionEvent event) {
		try {
			Stage stage = (Stage) root.getScene().getWindow();
			AboutBoxController controller=FXUtil.createWindow(stage, "About", Modality.APPLICATION_MODAL, AboutBoxController.class);
			controller.getStage().show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
        assert sourceComboBox != null : "fx:id=\"sourceComboBox\" was not injected: check your FXML file 'Window.fxml'.";
        assert targetComboBox != null : "fx:id=\"targetComboBox\" was not injected: check your FXML file 'Window.fxml'.";
        assert truncateTargetTableCheckBox != null : "fx:id=\"truncateTargetTableCheckBox\" was not injected: check your FXML file 'Window.fxml'.";
        assert currentTableProgressBar != null : "fx:id=\"currentTableProgressBar\" was not injected: check your FXML file 'Window.fxml'.";
        assert allTableProgressBar != null : "fx:id=\"allTableProgressBar\" was not injected: check your FXML file 'Window.fxml'.";
        assert connectionList != null : "fx:id=\"connectionList\" was not injected: check your FXML file 'Window.fxml'.";
        connectionList.setItems(FXCollections.observableArrayList(ConfUtil.conn));
        sourceComboBox.setItems(FXCollections.observableArrayList(ConfUtil.conn));
        targetComboBox.setItems(FXCollections.observableArrayList(ConfUtil.conn));
	}

	private int getRowCount(Connection scon, String stablename) throws SQLException {
		Statement select = scon.createStatement();
		ResultSet rowCount = select.executeQuery("select count('x') from "+stablename);
		rowCount.next();
		return rowCount.getInt(1);
		
	}

}
