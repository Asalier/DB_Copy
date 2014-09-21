package de.nrw.lichtenau.ian.db_copy.fx;

import java.io.FileNotFoundException;

import de.nrw.lichtenau.ian.db_copy.ConfUtil;
import de.nrw.lichtenau.ian.db_copy.DBProp;
import de.nrw.lichtenau.ian.db_copy.gui.Window;
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

    @FXML
    private TextField txtFldDriver;

    
    private DBProp auswahl;
    
    private Boolean cp;

	@FXML
    void onActionOk(ActionEvent event) {
		Stage stage = getStage();
		if(auswahl == null) {
			auswahl = new DBProp();
			ConfUtil.conn.add(auswahl);
		}
		auswahl.setName(txtFldName.getText());
		auswahl.setPass(txtFldPassword.getText());
		auswahl.setUrl(txtFldURL.getText());
		auswahl.setUser(txtFldUser.getText());
		auswahl.setDriver(txtFldDriver.getText());
		try {
			ConfUtil.writeconf();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		FIXME ian liste neu aufbauen
		stage.hide();
		
    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

	public void setAuswahl(DBProp auswahl, Boolean cp) {
		if(cp == false) {
			this.auswahl = auswahl;
		}
		if(auswahl != null) {
			this.txtFldName.setText(auswahl.getName());
			this.txtFldURL.setText(auswahl.getUrl());
			this.txtFldUser.setText(auswahl.getUser());
			this.txtFldPassword.setText(auswahl.getPass());
			this.txtFldDriver.setText(auswahl.getDriver());
		}else {
			this.txtFldName.setText("");
			this.txtFldURL.setText("");
			this.txtFldUser.setText("");
			this.txtFldPassword.setText("");
			this.txtFldDriver.setText("");
			
		}
	}
}
