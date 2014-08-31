package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXOptionPane {
	enum MESSAGE_TYPE {ERROR,WARN,INFO}
	
	public static void showWarningDlg(Stage parent, String title, String message) {
		
	}

	public static void showErrorDlg(Stage parent, String title, String message) {
		
	}

	public static void showMessageDlg(Stage parent, String title, String message) {
		FXOptionPane.showMessageDlg(parent, MESSAGE_TYPE.INFO, title, message);
	}
	
	public static void showMessageDlg(Stage parent, MESSAGE_TYPE type, String title, String message) {
		try {
			MessageDlgController controller=FXUtil.createWindow(parent, title, Modality.APPLICATION_MODAL, MessageDlgController.class);
			controller.getMessagePane().getChildren().add(new Text(message));
			controller.getStage().showAndWait();
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
