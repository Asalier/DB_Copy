package de.nrw.lichtenau.ian.db_copy.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXUtil {

	public static <T extends Controller> T createWindow(Stage parent, Stage stage, String title, Modality modality, Class<T> clazz) throws Exception {
		String fxmlName=clazz.getSimpleName().substring(0, clazz.getSimpleName().lastIndexOf("Controller"))+".fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(clazz.getResource(fxmlName));
		fxmlLoader.load();
		@SuppressWarnings("unchecked")
		T controller = (T)fxmlLoader.getController();
		controller.setStage(stage);
	
		Scene scene = new Scene(fxmlLoader.getRoot());
		stage.setTitle(title);
		stage.setScene(scene);
		if(parent!=null) {
			stage.initOwner(parent);
		}
		if(modality!=null) {
			stage.initModality(modality);
		}
		return controller;
	}

	public static <T extends Controller> T createWindow(Stage parent, String title, Modality modality, Class<T> clazz) throws Exception {
		return createWindow(parent, new Stage(), title, modality, clazz);
	}

}
