package Interface;

import java.awt.Desktop;
import java.io.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller {

	Desktop screen = Desktop.getDesktop();
	File tablature;
	TransformerHandler th;
	BufferedReader input;
	StreamResult output;

	@FXML
	Button browse, convert;

	@FXML
	Label path;

	@FXML
	public void handleButtonBrowse(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		tablature = fc.showOpenDialog(null);

		if (tablature != null) {
			path.setText(tablature.getAbsolutePath());
			try {
				screen.open(tablature);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void handleButtonConvert(ActionEvent event) {
		try {
			input = new BufferedReader(new FileReader(tablature));
			output = new StreamResult("tablature_converted.xml");
			createXML();
			String xmlLine = input.readLine();
			while (xmlLine != null) {
				process(xmlLine);
			}
			input.close();
			endXML();
		}
		catch (Exception e) {
			e.printStackTrace();
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
