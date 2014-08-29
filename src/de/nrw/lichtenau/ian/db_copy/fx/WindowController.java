package de.nrw.lichtenau.ian.db_copy.fx;

/**
 * Sample Skeleton for 'Window.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.nrw.lichtenau.ian.db_copy.Launch;

public class WindowController implements Controller {
	private Stage stage;

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	void onActionNew(ActionEvent event) {
		try {
			Launch.showWindow(stage, new Stage(), "DB Properties", Modality.APPLICATION_MODAL, DBPropDlgController.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onActionEdit(ActionEvent event) {

	}

	@FXML
	void onActionDelete(ActionEvent event) {

	}

	@FXML
	void onActionCopy(ActionEvent event) {

	}

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {

	}
}
