package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class AboutBoxController {
	
	@FXML
	private Parent root;


	public Stage getStage() {
		return (Stage) root.getScene().getWindow();
	}
	
}
