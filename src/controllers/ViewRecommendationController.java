package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import application.Main;

public class ViewRecommendationController implements Initializable{

    @FXML
    private TextArea textBox;

    @FXML
    private Button returnToEdit;

    @FXML
    private Button saveRec;

    Main main = new Main();
    
    
    /**
	 * Opens edit menu
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void openEditMenu(ActionEvent event) throws IOException {
    	main.switchScene("/controllers/fxml/EditRecommendation.fxml");
    }
    
    /**
	 * Handle if cancel button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void cancelButton(ActionEvent event) throws IOException {
    	main.switchScene("/controllers/fxml/HomePage.fxml");
    }

    /**
	 * Handle if save button is pressed
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void saveRec(ActionEvent event) throws IOException {
    	FileWriter recWriter = new FileWriter("src/resources/recs/" + CommonLibrary.recTitle);
    	recWriter.write(textBox.getText());
    	recWriter.close();
    	main.switchScene("/controllers/fxml/HomePage.fxml");
	}
    
    /**
	 * Initialize by creating a text file
	 * @param URL, ResourceBundle
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources){
		String fileName = CommonLibrary.recTitle;
		String line = "";
		
		File file = new File("src/resources/recs/" + fileName);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				line = scanner.nextLine();
				textBox.appendText(line + "\n");
				
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
