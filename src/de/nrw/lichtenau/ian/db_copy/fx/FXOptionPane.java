package de.nrw.lichtenau.ian.db_copy.fx;

import java.io.IOException;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

//FIXME mars: still missing confirmboxes with buttons defined by other developers.
/**
 * This class provides some easy to use static methods to interact with the user
 * like show messages, confirm messages or prompt the user for simple text input.
 * 
 * @author Markus Schulz
 *
 */
public final class FXOptionPane {
	private FXOptionPane() {
		//private constructor - do not create instances of this class
	}
	/** the type of dialogs (shown by different icons)*/
	enum MESSAGE_TYPE {ERROR,WARN,INFO}
	/** some predefined buttons */
	enum BUTTON {OK, CANCEL, YES, NO}
	
	/**
	 * Shows a warning message dialog with an Ok-Button.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the message dialog
	 * @param message the warning message
	 */
	public static void showWarningDlg(Stage parent, String title, String message) {
		FXOptionPane.showMessageDlg(parent, MESSAGE_TYPE.WARN, title, message);
	}

	/**
	 * Shows an error message dialog with an Ok-Button.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the message dialog
	 * @param message the error message
	 */
	public static void showErrorDlg(Stage parent, String title, String message) {
		FXOptionPane.showMessageDlg(parent, MESSAGE_TYPE.ERROR, title, message);
	}

	/**
	 * Shows a message dialog with an Ok-Button.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the message dialog
	 * @param message the message
	 */
	public static void showMessageDlg(Stage parent, String title, String message) {
		FXOptionPane.showMessageDlg(parent, MESSAGE_TYPE.INFO, title, message);
	}
	
	private static void showMessageDlg(Stage parent, MESSAGE_TYPE type, String title, String message) {
		try {
			MessageDlgController controller=FXUtil.createWindow(parent, title, Modality.APPLICATION_MODAL, MessageDlgController.class);
			controller.setType(type);
			controller.setButtonsVisible(BUTTON.OK);
			controller.showAnswerField(false);
			controller.getMessagePane().getChildren().add(new Text(message));
			controller.getStage().showAndWait();
	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows a confirm dialog with Ok- and Cancel-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmOkCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.INFO, title, message, BUTTON.OK, BUTTON.CANCEL);
	}
	
	/**
	 * Shows a confirm dialog with Yes- and No-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmYesNoDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.INFO, title, message, BUTTON.YES, BUTTON.NO);
	}
	
	/**
	 * Shows a confirm dialog with Yes-, No- and Cancel-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmYesNoCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.INFO, title, message, BUTTON.YES, BUTTON.NO, BUTTON.CANCEL);
	}
	
	/**
	 * Shows a warning confirm dialog with Ok- and Cancel-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmWarningOkCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.WARN, title, message, BUTTON.OK, BUTTON.CANCEL);
	}
	
	/**
	 * Shows a warning confirm dialog with Yes- and No-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmWarningYesNoDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.WARN, title, message, BUTTON.YES, BUTTON.NO);
	}
	
	/**
	 * Shows a warning confirm dialog with Yes-, No- and Cancel-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmWarningYesNoCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.WARN, title, message, BUTTON.YES, BUTTON.NO, BUTTON.CANCEL);
	}
	
	/**
	 * Shows an error confirm dialog with Ok- and Cancel-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmErrorOkCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.ERROR, title, message, BUTTON.OK, BUTTON.CANCEL);
	}
	
	/**
	 * Shows an error confirm dialog with Yes- and No-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmErrorYesNoDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.ERROR, title, message, BUTTON.YES, BUTTON.NO);
	}
	
	/**
	 * Shows an error confirm dialog with Yes-, No- and Cancel-Buttons.
	 * 
	 * @param parent the parent window where to display the centered confirm dialog.
	 * @param title the title of the confirm dialog
	 * @param message the message to confirm
	 * 
	 * @return the button clicked
	 */
	public static BUTTON showConfirmErrorYesNoCancelDlg(Stage parent, String title, String message) {
		return FXOptionPane.showConfirmDlg(parent, MESSAGE_TYPE.ERROR, title, message, BUTTON.YES, BUTTON.NO, BUTTON.CANCEL);
	}
	
	private static BUTTON showConfirmDlg(Stage parent, MESSAGE_TYPE type, String title, String message, BUTTON... buttons) {
		try {
			MessageDlgController controller=FXUtil.createWindow(parent, title, Modality.APPLICATION_MODAL, MessageDlgController.class);
			controller.setType(type);
			controller.setButtonsVisible(buttons);
			controller.showAnswerField(false);
			controller.getMessagePane().getChildren().add(new Text(message));
			controller.getStage().showAndWait();
			return controller.getClickedButton();
	
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Prompts the user for an answer.
	 * 
	 * @param parent the parent window where to display the centered prompt.
	 * @param title the title of the prompt dialog
	 * @param message the prompt
	 * 
	 * @return the user entered string, if (and only if) the user has confirmed the answer by pressing the "Ok"-Button - otherwise null.
	 */
	public static String showPromptDlg(Stage parent, String title, String message) {
		return showPromptDlg(parent, MESSAGE_TYPE.INFO, title, message);
	}
	
	/**
	 * Prompts the user to answer a question about a warning.
	 * 
	 * @param parent the parent window where to display the centered prompt.
	 * @param title the title of the prompt dialog
	 * @param message the prompt (the warning)
	 * 
	 * @return the user entered string, if (and only if) the user has confirmed the answer by pressing the "Ok"-Button - otherwise null.
	 */
	public static String showPromptWarningDlg(Stage parent, String title, String message) {
		return showPromptDlg(parent, MESSAGE_TYPE.WARN, title, message);
	}
	
	/**
	 * Prompts the user to answer a question about an error.
	 * 
	 * @param parent the parent window where to display the centered prompt.
	 * @param title the title of the prompt dialog
	 * @param message the prompt (the error)
	 * 
	 * @return the user entered string, if (and only if) the user has confirmed the answer by pressing the "Ok"-Button - otherwise null.
	 */
	public static String showPromptErrorDlg(Stage parent, String title, String message) {
		return showPromptDlg(parent, MESSAGE_TYPE.ERROR, title, message);
	}
	
	private static String showPromptDlg(Stage parent, MESSAGE_TYPE type, String title, String message) {
		try {
			MessageDlgController controller=FXUtil.createWindow(parent, title, Modality.APPLICATION_MODAL, MessageDlgController.class);
			controller.setType(type);
			controller.setButtonsVisible(BUTTON.OK, BUTTON.CANCEL);
			controller.showAnswerField(true);
			controller.getMessagePane().getChildren().add(new Text(message));
			controller.getStage().showAndWait();
			if(controller.getClickedButton()!=null && controller.getClickedButton()==BUTTON.OK) {
				return controller.getAnswer();
			}else {
				return null;
			}
	
		}catch(IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
