package de.nrw.lichtenau.ian.db_copy.fx;

import java.io.IOException;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXOptionPane {
	enum MESSAGE_TYPE {ERROR,WARN,INFO}
	enum BUTTON {OK, CANCEL, YES, NO}
	
	public static void showWarningDlg(Stage parent, String title, String message) {
		FXOptionPane.showMessageDlg(parent, MESSAGE_TYPE.WARN, title, message);
	}

	public static void showErrorDlg(Stage parent, String title, String message) {
		FXOptionPane.showMessageDlg(parent, MESSAGE_TYPE.ERROR, title, message);
	}

	public static void showMessageDlg(Stage parent, String title, String message) {
		FXOptionPane.showMessageDlg(parent, MESSAGE_TYPE.INFO, title, message);
	}
	
	private static void showMessageDlg(Stage parent, MESSAGE_TYPE type, String title, String message) {
		try {
			MessageDlgController controller=FXUtil.createWindow(parent, title, Modality.APPLICATION_MODAL, MessageDlgController.class);
			controller.setType(type);
			controller.setButtonsVisible(BUTTON.OK);
			controller.getMessagePane().getChildren().add(new Text(message));
			controller.getStage().showAndWait();
	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static BUTTON showConfirmOkCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.INFO, title, message, BUTTON.OK, BUTTON.CANCEL);
	}
	
	public static BUTTON showConfirmYesNoDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.INFO, title, message, BUTTON.YES, BUTTON.NO);
	}
	
	public static BUTTON showConfirmYesNoCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.INFO, title, message, BUTTON.YES, BUTTON.NO, BUTTON.CANCEL);
	}
	
	public static BUTTON showConfirmWarningOkCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.WARN, title, message, BUTTON.OK, BUTTON.CANCEL);
	}
	
	public static BUTTON showConfirmWarningYesNoDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.WARN, title, message, BUTTON.YES, BUTTON.NO);
	}
	
	public static BUTTON showConfirmWarningYesNoCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.WARN, title, message, BUTTON.YES, BUTTON.NO, BUTTON.CANCEL);
	}
	
	public static BUTTON showConfirmErrorOkCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.ERROR, title, message, BUTTON.OK, BUTTON.CANCEL);
	}
	
	public static BUTTON showConfirmErrorYesNoDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.ERROR, title, message, BUTTON.YES, BUTTON.NO);
	}
	
	public static BUTTON showConfirmErrorYesNoCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.ERROR, title, message, BUTTON.YES, BUTTON.NO, BUTTON.CANCEL);
	}
	
	private static BUTTON showConfirmDlg(Stage parent, MESSAGE_TYPE type, String title, String message, BUTTON... buttons) {
		try {
			MessageDlgController controller=FXUtil.createWindow(parent, title, Modality.APPLICATION_MODAL, MessageDlgController.class);
			controller.setType(type);
			controller.setButtonsVisible(buttons);
			controller.getMessagePane().getChildren().add(new Text(message));
			controller.getStage().showAndWait();
			return controller.getClickedButton();
	
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
