package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DBPropDlgController {
	@FXML
	private Parent root;
	
	public Stage getStage() {
		return (Stage) root.getScene().getWindow();
	}
	
    @FXML
    private TextField txtFldName;

    @FXML
    private TextField txtFldPassword;

    @FXML
    private TextField txtFldUser;

    @FXML
    private TextField txtFldURL;

	public void initialize() {
		txtFldName.setText("Yo - Alter");
	}

    @FXML
    void onActionOk(ActionEvent event) {
		Stage stage = getStage();
    	FXOptionPane.showMessageDlg(stage, "Hinweis", txtFldName.getText());
    	System.out.println(txtFldName.getText());
    	stage.hide();

    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }
}
