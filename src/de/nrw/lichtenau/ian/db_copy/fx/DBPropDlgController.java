package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DBPropDlgController implements Controller {
    @FXML
    private TextField txtFldName;

    @FXML
    private TextField txtFldPassword;

    @FXML
    private TextField txtFldUser;

    @FXML
    private TextField txtFldURL;

	private Stage stage;

    @Override
    public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}
	
	public void initialize() {
		txtFldName.setText("Yo - Alter");
	}

    @FXML
    void onActionOk(ActionEvent event) {
    	FXOptionPane.showMessageDlg(stage, "Hinweis", txtFldName.getText());
    	System.out.println(txtFldName.getText());
    	stage.hide();

    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }
}
