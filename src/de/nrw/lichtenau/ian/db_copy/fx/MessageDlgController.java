package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.nrw.lichtenau.ian.db_copy.fx.FXOptionPane.MESSAGE_TYPE;

public class MessageDlgController {
	@FXML
	private Parent root;
	
	public Stage getStage() {
		return (Stage) root.getScene().getWindow();
	}
	
    @FXML
    private ImageView iconInfo;

    @FXML
    private ImageView iconWarn;

    @FXML
    private ImageView iconError;

    @FXML
    private TextFlow messagePane;

	public TextFlow getMessagePane() {
		return this.messagePane;
	}
	
	public void setType(MESSAGE_TYPE type) {
		switch (type) {
		case INFO:
			iconInfo.setVisible(true);
			iconWarn.setVisible(false);
			iconError.setVisible(false);
			break;
		case WARN:
			iconInfo.setVisible(false);
			iconWarn.setVisible(true);
			iconError.setVisible(false);
			break;
		case ERROR:
			iconInfo.setVisible(false);
			iconWarn.setVisible(false);
			iconError.setVisible(true);
			break;
		}
	}
	
    @FXML
    void onActionOk(ActionEvent event) {
		Stage stage = getStage();
    	stage.close();
    }

}
