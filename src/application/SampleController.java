package application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

import javafx.event.ActionEvent;

public class SampleController {
	
	private Stage stage;
	private Scene scene;
	
	
	public void switchToScene1(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToCreateRec(ActionEvent event) throws IOException{

		Parent root = FXMLLoader.load(getClass().getResource("CreateRecommendation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
