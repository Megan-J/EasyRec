package application;
	
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
			mainStage.setResizable(false);
			mainStage.setTitle("EasyRec");
			mainStage.setScene(scene);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
