package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;

import java.io.IOException;

import application.controller.LoginController;

public class Main extends Application {
	
	private static Stage stage;
	
	public LoginController loginController;
	public Pane pane;
	
	public Main() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/Scene1.fxml"));
		pane = loader.load();
		loginController = (LoginController) loader.getController();
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			
			
			FXMLLoader loader = new RXMLLoader(getClass().getResource("gui/Login.fxml"));
			
			Parent root = FXMLLoader.load(getClass().getResource("gui/Login.fxml"));
			Scene scene = new Scene(root, Color.LIGHTBLUE);

			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
