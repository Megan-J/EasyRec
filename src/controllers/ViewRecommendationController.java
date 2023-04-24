package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ViewRecommendationController implements Initializable{

    @FXML
    private TextArea textBox;

    @FXML
    private Button returnToEdit;

    @FXML
    private Button saveRec;
    
    
    
    @FXML
    void openEditMenu(ActionEvent event) {
    	
    }

    @FXML
    void saveRec(ActionEvent event) {
		
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		String fileName = (String)stage.getUserData();
		String line = "";
		
		File file = new File("src/resources/recs/" + fileName);
		try {
			Scanner scanner = new Scanner(file);
			line = scanner.nextLine();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		textBox.setText(line);
		
	}
    

	@Override
	public void initialize(URL location, ResourceBundle resources){

	}
}
