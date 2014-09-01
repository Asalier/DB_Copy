package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MessageDlgController {
	@FXML
	private Parent root;
	
	public Stage getStage() {
		return (Stage) root.getScene().getWindow();
	}
	
    @FXML
    private ImageView typeIcon;

    @FXML
    private TextFlow messagePane;

	public TextFlow getMessagePane() {
		return this.messagePane;
	}
	
    @FXML
    void onActionOk(ActionEvent event) {
		Stage stage = getStage();
    	stage.close();
    }

}
