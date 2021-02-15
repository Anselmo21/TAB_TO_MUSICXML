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

public class Controller {

	Desktop screen = Desktop.getDesktop();
	File tablature;
	String getText;
	TransformerHandler th;
	BufferedReader input;
	StreamResult output;
	FileChooser fc;

	@FXML
	Button browse, convert, convertText, openFile;

	@FXML
	Label path;

	@FXML
	TextArea textbox;

	@FXML
	public void handleButtonBrowse(ActionEvent event) {
		fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		tablature = fc.showOpenDialog(null);

		if (tablature != null) {
			path.setText(tablature.getAbsolutePath());
			Scanner sc = null;
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

	@FXML
	public void handleButtonOpenFile(ActionEvent event) {
		fc = new FileChooser();
		Scanner sc = null;
		try {
			if (tablature != null) screen.open(tablature);
			sc = new Scanner(tablature);
			while (sc.hasNextLine()) 
				textbox.appendText(sc.nextLine() + "\n"); // else read the next token
		}
		catch(Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR); 
			errorAlert.setHeaderText("File Not Found!"); 
			errorAlert.setContentText("Please browse a file in order to open it!"); 
			errorAlert.showAndWait();
		}
	}



	@FXML
	public void handleButtonConvert(ActionEvent event) {
		try {
			input = new BufferedReader(new FileReader(tablature));
			output = new StreamResult("tablature_converted.musicxml");
			createXML();
			String xmlLine;
			while ((xmlLine = input.readLine()) != null) {
				process(xmlLine);

			}
			input.close();
			endXML();
		}
		catch (Exception e) {
			Alert errorAlert = new Alert(AlertType.ERROR); 
			errorAlert.setHeaderText("Input not valid!"); 
			errorAlert.setContentText("Provide text file."); 
			errorAlert.showAndWait();
		}
	}

	public void createXML() throws ParserConfigurationException, TransformerConfigurationException, SAXException {
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		th = tf.newTransformerHandler(); 

		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");

		th.setResult(output); 
		th.startDocument();
		th.startElement(null, null, "inserts", null); 
	}

	public void process(String s) throws SAXException {
		th.startElement(null, null, "note", null);  
		th.characters(s.toCharArray(), 0, s.length()); 
		th.endElement(null, null, "note"); 
	}

	public void endXML() throws SAXException {
		th.endElement(null, null, "inserts"); 
		th.endDocument(); 
	}

}
