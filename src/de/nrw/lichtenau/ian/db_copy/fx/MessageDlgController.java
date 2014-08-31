package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MessageDlgController implements Controller {

	private Stage stage;

    @Override
    public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Stage getStage() {
		return this.stage;
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
    	stage.close();
    }

}
