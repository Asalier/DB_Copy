package de.nrw.lichtenau.ian.db_copy;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import de.nrw.lichtenau.ian.db_copy.fx.FXUtil;
import de.nrw.lichtenau.ian.db_copy.fx.WindowController;

public class Launch extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		WindowController controller=FXUtil.createWindow(null, stage, "DB Copy", null, WindowController.class);
		controller.getStage().show();
	}
	
	public static void main(String[] args) {
		try {
			ConfUtil.readconf();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}
}
