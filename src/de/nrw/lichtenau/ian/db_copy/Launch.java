package de.nrw.lichtenau.ian.db_copy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.nrw.lichtenau.ian.db_copy.fx.Controller;
import de.nrw.lichtenau.ian.db_copy.fx.WindowController;

public class Launch extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Launch.showWindow(null, stage, "DB Copy", null, WindowController.class);
	}
	
	public static void showWindow(Stage parent, Stage stage, String title, Modality modality, Class<? extends Controller> clazz) throws Exception {
		String fxmlName=clazz.getSimpleName().substring(0, clazz.getSimpleName().lastIndexOf("Controller"))+".fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(clazz.getResource(fxmlName));
		fxmlLoader.load();
		Controller myController = (Controller)fxmlLoader.getController();
		myController.setStage(stage);

		Scene scene = new Scene(fxmlLoader.getRoot());
		stage.setTitle(title);
		stage.setScene(scene);
		if(parent!=null) {
			stage.initOwner(parent);
		}
		if(modality!=null) {
			stage.initModality(modality);
		}
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
