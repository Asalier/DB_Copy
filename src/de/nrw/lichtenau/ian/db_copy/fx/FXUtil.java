package de.nrw.lichtenau.ian.db_copy.fx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXUtil {

	/**
	 * Creates a window and returns its controller, if the fxml file is named like the window and its 
	 * controller is named like the window followed by "Controller" - e&#46;g&#46; MyDialog.fxml / MyDialogController. 
	 * 
	 * @param <T> the controller type
	 * 
	 * @param parent the stage of the parent window, to paint the new window on
	 * @param stage the stage to paint the new window on
	 * @param title the title of the new window
	 * @param modality the modality of the new window
	 * @param controllerClass the controller class of the new window
	 * 
	 * @return the controller of the new window
	 * 
	 * @throws IOException if the fxml file can't be loaded
	 */
	public static <T> T createWindow(Stage parent, Stage stage, String title, Modality modality, Class<T> controllerClass) throws IOException {
		String fxmlName=controllerClass.getSimpleName().substring(0, controllerClass.getSimpleName().lastIndexOf("Controller"))+".fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(controllerClass.getResource(fxmlName));
		fxmlLoader.load();
	
		Scene scene = new Scene(fxmlLoader.getRoot());
		stage.setTitle(title);
		stage.setScene(scene);
		if(parent!=null) {
			stage.initOwner(parent);
		}
		if(modality!=null) {
			stage.initModality(modality);
		}
		@SuppressWarnings("unchecked")
		T controller = (T)fxmlLoader.getController();
		return controller;
	}

	/**
	 * Creates a window and returns its controller, if the fxml file is named like the window and its 
	 * controller is named like the window followed by "Controller" - e&#46;g&#46; MyDialog.fxml / MyDialogController. 
	 * 
	 * @param <T> the controller type
	 * 
	 * @param parent the stage of the parent window, to paint the new window on
	 * @param title the title of the new window
	 * @param modality the modality of the new window
	 * @param controllerClass the controller class of the new window
	 * 
	 * @return the controller of the new window
	 * 
	 * @throws IOException if the fxml file can't be loaded
	 */
	public static <T> T createWindow(Stage parent, String title, Modality modality, Class<T> controllerClass) throws IOException {
		return createWindow(parent, new Stage(), title, modality, controllerClass);
	}

}
