package Interface;

import java.awt.Desktop;

import java.io.*;
import java.util.*;

import javax.xml.transform.stream.StreamResult;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import TAB_TO_XML.App;

public class Controller {

	ObservableList<String> instrumentsList = FXCollections.observableArrayList("None", "Guitar", "Drums", "Bass");
	ObservableList<String> timeSignatureList = FXCollections.observableArrayList("1/4", "2/4", "3/4", "4/4");

	Desktop screen = Desktop.getDesktop();
	File tablature;
	File xmlFile;
	BufferedReader input;
	StreamResult output;
	FileChooser fc, saveFile;
	static String obtainText;
	static boolean pressed;
	static String time;

	@SuppressWarnings("rawtypes")
	@FXML
	ChoiceBox timeSignature;

	@FXML
	Button browse, convert, save, edits;

	@FXML
	Label path, getInstrument;

	@FXML
	TextArea view, write;

	@SuppressWarnings("rawtypes")
	@FXML
	ChoiceBox instrumentBox;



	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		timeSignature.setItems(timeSignatureList);
		time = (String)timeSignature.getValue();
		instrumentBox.setItems(instrumentsList);
		String instrument = App.identifyInstrument(App.getFileList(write.getText()));
		if (instrument.equals("Guitar")) instrumentBox.setValue("Guitar");
		else if (instrument.equals("Drums")) instrumentBox.setValue("Drums");
		else if (instrument.equals("Bass")) instrumentBox.setValue("Bass");
		else instrumentBox.setValue("None");
	}



	/**
	 * This method enables browsing through the file explorer for .txt files. After browsing, it shows the text on the file in a 
	 * text area. 
	 * @param event
	 */

	@FXML
	public void handleButtonBrowse(ActionEvent event) {
		write.clear();
		pressed = true;
		fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		tablature = fc.showOpenDialog(null);

		if (tablature != null) {
			//path.setText(tablature.getAbsolutePath());
			Scanner sc = null;
			save.setVisible(true);
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
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
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

	@FXML
	public void handleButtonSaveEdits(ActionEvent event) {
		PrintWriter print = null;
		if (pressed) {
			//save edits in the browsed path file.
			try {
				print = new PrintWriter(tablature.getAbsolutePath());
				print.print(write.getText());
				System.out.println(write.getText());
			}
			catch (Exception e) {
				Alert errorAlert = new Alert(AlertType.ERROR); 
				errorAlert.setHeaderText("Edits could not be saved!"); 
				errorAlert.setContentText("A file path was not found. Please check if you have browsed properly!"); 
				errorAlert.showAndWait();
			}
		}
		else {
			//make a new file and save edits there
			FileChooser saveFile = new FileChooser();
			saveFile.getExtensionFilters().add(new ExtensionFilter("Text file", "*.txt"));
			File xmlFile = saveFile.showSaveDialog(null);
			try {
				if (xmlFile.getAbsolutePath() != null) {
					print = new PrintWriter(xmlFile.getAbsolutePath());
					print.println(write.getText());
					System.out.println(write.getText());
				}
			}
			catch (Exception e) {
				Alert errorAlert = new Alert(AlertType.ERROR); 
				errorAlert.setHeaderText("Edits could not be saved!"); 
				errorAlert.setContentText("A file path was not found. Please check if you have selected a valid file path!"); 
				errorAlert.showAndWait();
			}
		}
	}

}