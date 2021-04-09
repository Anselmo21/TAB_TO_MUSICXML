package Interface;

import java.awt.Desktop;

import java.io.*;
import java.util.*;

import javax.xml.transform.stream.StreamResult;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import TAB_TO_XML.App;

public class Controller {

	private static Window window = new Stage();
	Desktop screen = Desktop.getDesktop();
	File tablature;
	File xmlFile;
	BufferedReader input;
	StreamResult output;
	FileChooser fc, saveFile;
	static String obtainText;

	@FXML
	Button browse, convert, save;

	@FXML
	Label path, getInstrument;

	@FXML
	TextArea view, write, customization;
	
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		save.setDisable(true);
	}



	/**
	 * This method enables browsing through the file explorer for .txt files. After browsing, it shows the text on the file in a 
	 * text area. 
	 * @param event
	 */

	@FXML
	public void handleButtonBrowse(ActionEvent event) {
		save.setDisable(true);
		write.clear();
		getInstrument.setText("");
		fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		browse.setDisable(true);
		tablature = fc.showOpenDialog(null);
		if (tablature != null) {
			Scanner sc = null;
			try {
				sc = new Scanner(tablature);
				while (sc.hasNextLine()) {
					write.appendText(sc.nextLine() + "\n"); // else read the next token
				}				
				String instrument = App.identifyInstrument(App.getFileList(write.getText()));
				if (instrument.equals("Guitar")) getInstrument.setText("Instrument: Guitar");
				else if (instrument.equals("Drums")) getInstrument.setText("Instrument: Drums");
				else if (instrument.equals("Bass")) getInstrument.setText("Instrument: Bass");
				else getInstrument.setText("No Instrument Found");
				browse.setDisable(false);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		browse.setDisable(false);
	}


	/**
	 * This method handles the convert button in the Graphic User Interface. It shows an alert once the file is converted.
	 * @param event
	 */

	@FXML
	public void handleButtonConvert(ActionEvent event) {
		view.clear();
		obtainText = write.getText();
		try {
			if (write.getText() != "") {
				String storeText = write.getText();
				String instrument = App.identifyInstrument(App.getFileList(storeText));
				if (instrument.equals("Guitar")) getInstrument.setText("Instrument: Guitar");
				else if (instrument.equals("Drums")) getInstrument.setText("Instrument: Drums");
				else if (instrument.equals("Bass")) getInstrument.setText("Instrument: Bass");
				else getInstrument.setText("No Instrument Found");
				String getConversion = App.runConversion(storeText);
				String getCustomization = customization.getText();
				App.getTimeSignatures(getCustomization);
				view.appendText(getConversion);
				save.setDisable(false);
			}
			else {
				Alert errorAlert = new Alert(AlertType.ERROR); 
				errorAlert.setHeaderText("Input not valid!"); 
				errorAlert.setContentText("Provide text file or paste tablature on the textbox to your left."); 
				errorAlert.showAndWait();
				errorAlert.close();
			}
		}
		catch (Exception e) {
		}
	}

	/**
	 * This is the method that controls the save button in the User Interface.
	 * @param event
	 */
	@FXML
	public void handleButtonSave(ActionEvent event) {
		saveFile = new FileChooser();
		saveFile.getExtensionFilters().add(new ExtensionFilter("musicXML files", "*.musicxml"));
		xmlFile = saveFile.showSaveDialog(null);
		PrintWriter write = null;
		try {
			if (xmlFile.getAbsolutePath() != null) {
				write = new PrintWriter(xmlFile.getAbsolutePath());
				write.println(view.getText());
				Alert saveAlert = new Alert(AlertType.CONFIRMATION); //creates a displayable error allert window
				saveAlert.setHeaderText("The converted file has been saved to " + xmlFile.getAbsolutePath()); 
				saveAlert.setContentText("Thank you for using Allegro Tab Converter!"); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
				saveAlert.showAndWait();
				write.close();
			}
			else {
				Alert errorAlert = new Alert(AlertType.ERROR); 
				errorAlert.setHeaderText("File cannot be saved!"); 
				errorAlert.setContentText("Please convert a file in order to save it!"); 
				errorAlert.showAndWait();
			}
		}
		catch(Exception e) {
		}
	}
	

	public void handleErrors() {
		//
	}
	
	public Window openNewWindow(String fxml, String name) {
		try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));

            Stage stage = new Stage();
            stage.setTitle(name);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Runner.stage);
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return scene.getWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
}