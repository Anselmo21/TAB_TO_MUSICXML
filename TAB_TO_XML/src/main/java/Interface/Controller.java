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
import TAB_TO_XML.App;
import TAB_TO_XML.Parser;

public class Controller {

	Desktop screen = Desktop.getDesktop();
	File tablature;
	String getText;
	TransformerHandler th;
	BufferedReader input;
	StreamResult output;
	FileChooser fc;

	@FXML
	Button browse, convert, save;

	@FXML
	Label path;

	@FXML
	TextArea textbox;

	/**
	 * This method enables browsing through the file explorer for .txt files. After browsing, it shows the text on the file in a 
	 * text area. 
	 * @param event
	 */

	@FXML
	public void handleButtonBrowse(ActionEvent event) {
		textbox.clear();
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
					textbox.appendText(sc.nextLine() + "\n"); // else read the next token
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
			input = new BufferedReader(new FileReader(tablature));
			output = new StreamResult("tablature_converted.musicxml");
			//createXML();
			String storePath = tablature.getAbsolutePath();
			Parser.setPath(storePath);
			App.main(null);
			Alert errorAlert = new Alert(AlertType.CONFIRMATION); //creates a displayable error allert window 
			errorAlert.setHeaderText("The selected file is being converted to XML"); 
			errorAlert.setContentText("Please Wait.."); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
			errorAlert.showAndWait();
			textbox.clear();
		}
		catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR); 
			errorAlert.setHeaderText("Input not valid!"); 
			errorAlert.setContentText("Provide text file."); 
			errorAlert.showAndWait();
			textbox.clear();
		}
	}
	
	@FXML
	public void handleButtonSave(ActionEvent event) {
		
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
