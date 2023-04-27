package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	public static Stage stg;
	
	@Override
	public void start(Stage mainStage) {
		try {
			stg = mainStage;
			Parent root = FXMLLoader.load(getClass().getResource("/controllers/fxml/Login.fxml"));
			Scene scene = new Scene(root, 1240, 800);
			stg.setResizable(false);
			stg.setTitle("EasyRec");
			stg.setScene(scene);
			stg.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchScene(String fxml) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(parent, 1240, 800);
		stg.setScene(scene);
		stg.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
