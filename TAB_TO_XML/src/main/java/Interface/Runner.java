package Interface;


import java.io.File;
import java.io.PrintWriter;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import Interface.Controller;


public class Runner extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interface/Intro.fxml"));
		primaryStage.setTitle("Allegro Tab Converter");
		Scene scene = new Scene(root, 935, 700);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("Interface/Intro.css").toExternalForm());
		Image logo = new Image("/Interface/music-background.jpg");
		primaryStage.getIcons().add(logo);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> closeProgram());
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void closeProgram() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("This action will close the application.");
		alert.setContentText("Do you want to save any edits before closing?");

		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(yes, no);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == yes){
			FileChooser saveFile = new FileChooser();
			saveFile.getExtensionFilters().add(new ExtensionFilter("Text file", "*.txt"));
			File xmlFile = saveFile.showSaveDialog(null);
			PrintWriter write =  null;
			try {
				if (xmlFile.getAbsolutePath() != null) {
					write = new PrintWriter(xmlFile.getAbsolutePath());
					write.println(Controller.getTextArea());
					Alert saveAlert = new Alert(AlertType.CONFIRMATION); //creates a displayable error allert window
					saveAlert.setHeaderText("The converted file has been saved to " + xmlFile.getAbsolutePath()); 
					saveAlert.setContentText("Thank you for using Allegro Tab Converter!"); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
					saveAlert.showAndWait();
					write.close();
				}
				else {
				}
			}
			catch(Exception e) {
			}
		} 
		else {
		    alert.close();
		}
	}
}


