package de.nrw.lichtenau.ian.db_copy.fx;

/**
 * Sample Skeleton for 'Window.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
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
			
			controller.getStage().show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onActionEdit(ActionEvent event) {
		try {
			Stage stage = (Stage) root.getScene().getWindow();
			
			DBPropDlgController controller=FXUtil.createWindow(stage, "DB Properties", Modality.APPLICATION_MODAL, DBPropDlgController.class);
			controller.getStage().show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onActionDelete(ActionEvent event) {
		
	}

	@FXML
	void onActionCopy(ActionEvent event) {
		try {
			Stage stage = (Stage) root.getScene().getWindow();
			DBPropDlgController controller=FXUtil.createWindow(stage, "DB Properties", Modality.APPLICATION_MODAL, DBPropDlgController.class);
			controller.getStage().show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    @FXML
    void onActionCopyDB(ActionEvent event) {

//    	if(truncateTargetTableCheckBox.isSelected()) {
//			Statement truncate=tcon.createStatement();
//			truncate.executeUpdate("truncate table "+tabellenname);
//
//    	}

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
}
