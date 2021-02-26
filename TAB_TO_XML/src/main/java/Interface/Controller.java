package Interface;

import java.awt.Desktop;

import java.io.*;
import java.net.URL;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import TAB_TO_XML.App;
import TAB_TO_XML.Parser;

public class Controller {

	Desktop screen = Desktop.getDesktop();
	Window window;
	File tablature;
	File xmlFile;
	BufferedReader input;
	StreamResult output;
	FileChooser fc;

	@FXML
	Button browse, convert, save;

	@FXML
	Label path;

	@FXML
	TextArea view, write;
	/**
	 * This method enables browsing through the file explorer for .txt files. After browsing, it shows the text on the file in a 
	 * text area. 
	 * @param event
	 */

	@FXML
	public void handleButtonBrowse(ActionEvent event) {
		//write.clear();
		fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		tablature = fc.showOpenDialog(null);

		if (tablature != null) {
			path.setText(tablature.getAbsolutePath());
			Scanner sc = null;
			convert.setVisible(true);
			try {
				sc = new Scanner(tablature);
				while (sc.hasNextLine()) {
					write.appendText(sc.nextLine() + "\n"); // else read the next token
				}
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
		try {
			if (tablature != null) {
				//			input = new BufferedReader(new FileReader(tablature));
				//			output = new StreamResult("tablature_converted.musicxml");
				String storePath = tablature.getAbsolutePath();
				Parser.setPath(storePath);
				App.main(null);
				String getConversion = App.getConversion();
				view.appendText(getConversion);
				Alert convertAlert = new Alert(AlertType.CONFIRMATION); //creates a displayable error allert window 
				convertAlert.setHeaderText("The selected file is being converted to XML"); 
				convertAlert.setContentText("Please Wait.."); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
				convertAlert.showAndWait();
			}
			else {
				
			}
		}
		catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR); 
			errorAlert.setHeaderText("Input not valid!"); 
			errorAlert.setContentText("Provide text file."); 
			errorAlert.showAndWait();
		}
	}

	/**
	 * This is the method that controls the save button in the User Interface.
	 * @param event
	 */
	@FXML
	public void handleButtonSave(ActionEvent event) {
		FileChooser downloadDestination = new FileChooser();
		FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("musicXML files (*.musicxml)", "*.musicxml");
		downloadDestination.getExtensionFilters().add(extension);
		xmlFile = downloadDestination.showSaveDialog(window);
		try {
			while (xmlFile != null) {
				FileWriter write = new FileWriter(xmlFile);
				write.write(view.getText());
				write.close();
			}
			Alert saveAlert = new Alert(AlertType.CONFIRMATION); //creates a displayable error allert window 
			saveAlert.setHeaderText("The converted file has been saved to " + xmlFile.getAbsolutePath()); 
			saveAlert.setContentText("Thank you for using Allegro Tab Converter!"); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
			saveAlert.showAndWait();
		}
		catch(Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR); 
			errorAlert.setHeaderText("File cannot be saved!"); 
			errorAlert.setContentText("Please convert a file in order to save it!"); 
			errorAlert.showAndWait();
		}

	}


	public String getPath(String path) {
		return tablature.getAbsolutePath();
	}

}

/*Convert file
 * 	fc = new FileChooser();
		try {
			if (tablature != null) screen.open(tablature);
		}
		catch(Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR); 
			errorAlert.setHeaderText("File Not Found!"); 
			errorAlert.setContentText("Please browse a file in order to open it!"); 
			errorAlert.showAndWait();
		}
 */
