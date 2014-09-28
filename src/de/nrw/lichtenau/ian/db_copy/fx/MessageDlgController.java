package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import de.nrw.lichtenau.ian.db_copy.fx.FXOptionPane.BUTTON;
import de.nrw.lichtenau.ian.db_copy.fx.FXOptionPane.MESSAGE_TYPE;

public class MessageDlgController {
	@FXML
	private Parent root;
	
    @FXML
    private Button btnOk;//is "Ok" or "Yes"

    @FXML
    private Button btnNo;//is "Ok" or "Yes"

    @FXML
    private Button btnCancel;

    @FXML
    private HBox btnPane;

    @FXML
    private ImageView iconInfo;

    @FXML
    private ImageView iconWarn;

    @FXML
    private ImageView iconError;

    @FXML
    private TextFlow messagePane;

    private boolean okButtonIsYesButton;
	private BUTTON clickedButton;

	public Stage getStage() {
		return (Stage) root.getScene().getWindow();
	}
	
	public TextFlow getMessagePane() {
		return this.messagePane;
	}
	
	public void setButtonsVisible(BUTTON... buttons) {
		btnPane.getChildren().remove(btnOk);
		btnPane.getChildren().remove(btnNo);
		btnPane.getChildren().remove(btnCancel);
		for(BUTTON button:buttons) {
			switch (button) {
			case YES:
				okButtonIsYesButton=true;
				btnOk.setText("Yes");
				btnPane.getChildren().add(btnOk);
				break;
			case OK:
				okButtonIsYesButton=false;
				btnOk.setText("Ok");
				btnPane.getChildren().add(btnOk);
				break;
			case CANCEL:
				btnPane.getChildren().add(btnCancel);
				break;
			case NO:
				btnPane.getChildren().add(btnNo);
				break;
			}
		}
	}

	public BUTTON getClickedButton() {
		return clickedButton;
	}
	
	public void setType(MESSAGE_TYPE type) {
		iconInfo.setVisible(false);
		iconWarn.setVisible(false);
		iconError.setVisible(false);
		switch (type) {
		case INFO:
			iconInfo.setVisible(true);
			break;
		case WARN:
			iconWarn.setVisible(true);
			break;
		case ERROR:
			iconError.setVisible(true);
			break;
		}
	}
	
    @FXML
    void onActionOk(ActionEvent event) {
    	if(okButtonIsYesButton) {
    		clickedButton=BUTTON.YES;
    	}else {
    		clickedButton=BUTTON.OK;
    	}
		getStage().close();
    }
	
    @FXML
    void onActionNo(ActionEvent event) {
    	clickedButton=BUTTON.NO;
		getStage().close();
    }
	
    @FXML
    void onActionCancel(ActionEvent event) {
    	clickedButton=BUTTON.CANCEL;
		getStage().close();
    }

}
