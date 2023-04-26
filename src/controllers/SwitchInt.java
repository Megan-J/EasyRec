package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface SwitchInt {
	public static final Stage stg = new Stage();
	public default void switchScene(String fxml) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(parent, 1240, 800);
		stg.setScene(scene);
		stg.show();
	}
}
